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

}