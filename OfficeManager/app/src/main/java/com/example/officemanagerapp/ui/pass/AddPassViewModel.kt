package com.example.officemanagerapp.ui.pass

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.HomeRVItem
import com.example.officemanagerapp.models.Pass
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.PassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPassViewModel @Inject constructor(
    private val repository: PassRepository
): ViewModel() {

    private val _passResponse = MutableLiveData<Resource<Pass>>()
    val passResponse: LiveData<Resource<Pass>>
        get() = _passResponse

    fun postPass() = viewModelScope.launch {
        _passResponse.value = Resource.Loading
        val pass = Pass(0, fullName.value, phone.value, dateStart.value, dateEnd.value)

        delay(1000)
        _passResponse.value = Resource.Success(pass)
        //_passResponse.value = repository.postPass(pass)
    }

    val fullName: MutableLiveData<String> = MutableLiveData()
    val phone: MutableLiveData<String> = MutableLiveData()
    val dateStart: MutableLiveData<String> = MutableLiveData()
    val dateEnd: MutableLiveData<String> = MutableLiveData()

}