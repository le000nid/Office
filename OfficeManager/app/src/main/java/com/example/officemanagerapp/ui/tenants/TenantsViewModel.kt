package com.example.officemanagerapp.ui.tenants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.Tenant
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.TenantsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TenantsViewModel @Inject constructor(
    private val repository: TenantsRepository
): ViewModel() {

    private val _tenantsListLiveData: MutableLiveData<Resource<List<Tenant>>> = MutableLiveData()
    val tenantsListLiveData: LiveData<Resource<List<Tenant>>>
        get() = _tenantsListLiveData

    init {
        getTenants()
    }

    private fun getTenants() = viewModelScope.launch {
        _tenantsListLiveData.value = Resource.Loading

        val testData = listOf(
            Tenant("Компания 1", 1, "", "Информация 1"),
            Tenant("Компания 2", 2, "", "Информация 1"),
            Tenant("Компания 3", 3, "", "Информация 1"),
            Tenant("Компания 4", 4, "", "Информация 1"),
        )
        delay(1000)
        _tenantsListLiveData.value = Resource.Success(testData)

        // todo (uncomment when api will be ready)
        // _tenantsListLiveData.value = repository.getTenants()
    }
}