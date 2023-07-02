package com.example.gesuaschallenge.register.dataRegister

import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gesuaschallenge.domain.PreferenceHelper
import com.example.gesuaschallenge.domain.ValidatorHelper
import com.example.gesuaschallenge.domain.mask.Mask
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataRegisterViewModel @Inject constructor(
    private val validatorHelper: ValidatorHelper,
    private val mask: Mask,
    private val preferenceHelper: PreferenceHelper
): ViewModel() {

    val birthDateTextWatcher = MutableLiveData<TextWatcher>()
    val cellphoneTextWatcher = MutableLiveData<TextWatcher>()
    val cpfTextWatcher = MutableLiveData<TextWatcher>()
    val registerButtonEnabled = MutableLiveData(false)

    val showErrorOnCPF = MutableLiveData(false)
    val showErrorOnName = MutableLiveData(false)
    val showErrorOnCellphone = MutableLiveData(false)

    private val name = MutableLiveData<String>()
    val birthDate = MutableLiveData<String>()
    val cpf = MutableLiveData<String>()
    val cellphone = MutableLiveData<String>()
    val goNextView = MutableLiveData<Unit>()


    init {
        birthDateTextWatcher.postValue(
            mask.applyMask("##/##/####") {
                birthDate.postValue(it)
                if (isDigitValidated(it)) {
                    preferenceHelper.setBirthDate(it)
                    validateFields()
                }
            }
        )

        cellphoneTextWatcher.postValue(
            mask.applyMask("(##) #####-####") {
                cellphone.postValue(it)
                showErrorOnCellphone.postValue(false)
                if (isDigitValidated(it)) {
                    preferenceHelper.setCellphone(it)
                    validateCellphone(it)
                }
            }
        )

        cpfTextWatcher.postValue(
            mask.applyMask("###.###.###-##") {
                cpf.postValue(it)
                showErrorOnCPF.postValue(false)
                if (isDigitValidated(it)) {
                    preferenceHelper.setCPF(it)
                    validateCPF(it)
                }
            }
        )
    }

    private fun isDigitValidated(text: String): Boolean {
        for(c: Char in mask.lastMaskUsed.toCharArray()){
            if(c != '#'){
                if(!text.contains(c)){
                    return false
                }
            }
        }
        if(text.length == mask.lastMaskUsed.length) {
            return true
        }
        return false
    }

    private fun validateCPF(cpf: String) {
        if (validatorHelper.validateCPF(cpf)) {
            validateFields()
        } else {
            showErrorOnCPF.postValue(true)
        }
    }

    private fun validateCellphone(cellphone: String) {
        if(validatorHelper.validateCellphone(cellphone)) {
            validateFields()
        } else {
            showErrorOnCellphone.postValue(true)
        }
    }

    private fun validateFields() {
        if (!name.value.isNullOrBlank() && name.value?.substringAfter(' ')?.isNotEmpty() == true) {
            validatorHelper.apply {
                if (name.value?.substringAfter(' ')?.isNotEmpty() == true) {
                    preferenceHelper.setName(name.value.toString())
                    registerButtonEnabled.postValue(validateCPF(cpf.value) && validateCellphone(cellphone.value))
                } else {
                    showErrorOnName.postValue(true)
                }
            }
        }
    }

    fun registerClicked() {
        goNextView.postValue(Unit)
    }

    fun setName(name: String) {
        this.name.value = name
        showErrorOnName.postValue(false)
        validateFields()
    }

}