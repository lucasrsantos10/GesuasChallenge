package com.example.gesuaschallenge.domain

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferenceHelper@Inject constructor(
    @ApplicationContext context: Context
) {

    companion object {
        private const val SHARED_PREFERENCES_NAME = "CardGreenPrefFile"

        private const val NAME = "name"
        private const val BIRTH_DATE = "birthDate"
        private const val CPF = "cpf"
        private const val CELLPHONE = "cellphone"
    }

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setBirthDate(birthDate: String) {
        sharedPreferences.edit {
            putString(BIRTH_DATE, birthDate)
        }
    }

    fun getBirthDate(): String? {
        return sharedPreferences.getString(BIRTH_DATE, null)
    }

    fun setCellphone(cellphone: String) {
        sharedPreferences.edit {
            putString(CELLPHONE, cellphone)
        }
    }

    fun setCPF(cpf: String) {
        sharedPreferences.edit {
            putString(CPF, cpf)
        }
    }

    fun getCPF(): String? {
        return sharedPreferences.getString(CPF, null)
    }

    fun setName(name: String) {
        sharedPreferences.edit {
            putString(NAME, name)
        }
    }

    fun getName(): String? {
        return sharedPreferences.getString(NAME, null)
    }
}