package com.example.officemanagerapp.ui.services.market.companies_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.Company
import com.example.officemanagerapp.models.Review
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.ServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompaniesListViewModel @Inject constructor(
    private val repository: ServicesRepository
): ViewModel() {

    private val _companiesListLiveData = MutableLiveData<Resource<List<Company>>>()
    val companiesListLiveData: LiveData<Resource<List<Company>>>
        get() = _companiesListLiveData

    init {
        getCompaniesList()
    }

    private fun getCompaniesList() = viewModelScope.launch {
        _companiesListLiveData.value = Resource.Loading

        val reviewList = listOf(
            Review("Алексей Петрович", "", 5.0f, "12.12.21","Все огонь"),
            Review("Алексей Петрович", "", 5.0f, "12.12.21","Все огонь"),
            Review("Алексей Петрович", "", 5.0f, "12.12.21","Все огонь"),
        )

        val testListCompanies = listOf(
            Company("Компания 1", "", 4.24f, 12, "Инфорация о компании", "от 2000р",reviewList),
            Company("Компания 2", "", 3.54f, 2, "Инфорация о компании", "от 5000р",reviewList),
            Company("Компания 3", "", 2.70f, 16, "Инфорация о компании", "от 1000р", null),
            Company("Компания 4", "", 4.54f, 121, "Инфорация о компании", "от 2500р", null),
        )

        delay(1000)

        _companiesListLiveData.value = Resource.Success(testListCompanies)

        // todo (uncomment when api will be ready)
        // todo (pass category id from safe args)
        // _companiesListLiveData.value = repository.getCompanies(1)
    }


}