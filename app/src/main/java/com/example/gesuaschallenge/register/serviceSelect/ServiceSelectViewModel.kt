package com.example.gesuaschallenge.register.serviceSelect

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceSelectViewModel @Inject constructor() : ViewModel() {

    val enableContinueButton = MutableLiveData(false)

    fun serviceChoosed() {
        enableContinueButton.postValue(true)
    }

}