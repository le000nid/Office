package com.example.officemanagerapp.ui.pass

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPassViewModel @Inject constructor(

): ViewModel() {

    val txInputName: MutableLiveData<String> = MutableLiveData()
    val txInputPhone: MutableLiveData<String> = MutableLiveData()
    val txInputDateStart: MutableLiveData<String> = MutableLiveData()
    val txInputDateEnd: MutableLiveData<String> = MutableLiveData()

    private val formErrors: MutableList<FormErrors> = mutableListOf()

    fun getFormErrors(): List<FormErrors> {
        formErrors.clear()

        if (txInputName.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_NAME)
        }

        if (txInputPhone.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_PHONE)
        }

        if (txInputDateStart.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_DATE_START)
        }

        if (txInputDateEnd.value.isNullOrEmpty()) {
            formErrors.add(FormErrors.MISSING_DATE_END)
        }

        return formErrors
    }

    enum class FormErrors {
        MISSING_NAME,
        MISSING_PHONE,
        MISSING_DATE_START,
        MISSING_DATE_END
    }
}