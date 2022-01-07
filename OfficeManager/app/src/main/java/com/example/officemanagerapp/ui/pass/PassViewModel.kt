package com.example.officemanagerapp.ui.pass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.Pass
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.PassRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class PassViewModel (
    private val repository: PassRepository
): ViewModel() {

    private val _passListLiveData: MutableLiveData<Resource<List<Pass>>> = MutableLiveData()
    val passListLiveData: LiveData<Resource<List<Pass>>>
        get() = _passListLiveData

    init {
        // todo(remove hardcode and add actual date)
        getPasses("12 февраля 2021")
    }

    fun getPasses(date: String) = viewModelScope.launch {
        _passListLiveData.value = Resource.Loading

        val testData = listOf(
            Pass(1213, "24.11.21", "26.11.21", "Александр Петрович", "89999999999"),
            Pass(1215, "25.11.21", "27.11.21", "Александр Петрович", "89999999999"),
            Pass(1217, "26.11.21", "28.11.21", "Александр Петрович", "89999999999"),
            Pass(1222, "27.11.21", "29.11.21", "Александр Петрович", "89999999999"),
        )
        delay(1000)
        _passListLiveData.value = Resource.Success(testData)

        // todo (uncomment when api will be ready)
        //_passListLiveData.value = repository.getPasses(date)
    }
}