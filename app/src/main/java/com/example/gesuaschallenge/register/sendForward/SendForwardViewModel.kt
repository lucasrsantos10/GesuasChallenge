package com.example.gesuaschallenge.register.sendForward

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gesuaschallenge.domain.PreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SendForwardViewModel @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) : ViewModel() {

    val forwardButtonEnabled = MutableLiveData(false)
    val name = MutableLiveData("")
    val cpf = MutableLiveData("")
    val birthDate = MutableLiveData("")
    val age = MutableLiveData("")


    init {
//        getInformations()
    }

    fun setReasons() {
        forwardButtonEnabled.postValue(true)
    }

    private fun getInformations() {
        cpf.postValue(preferenceHelper.getCPF())
        name.postValue(preferenceHelper.getName())
        birthDate.postValue(preferenceHelper.getBirthDate())

        val date = birthDate.value?.split('/', ignoreCase = true)
        val today = Calendar.getInstance()

        if (date != null) {
            var todayAge = today.get(Calendar.YEAR) - date[2].toInt()
            if (today.get(Calendar.MONTH) > date[1].toInt() ||
                today.get(Calendar.DAY_OF_MONTH) > date[0].toInt()) {
                todayAge--
            }
            age.postValue(todayAge.toString())
        }
    }

}