package com.example.officemanagerapp.ui.services.planned

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.Category
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.ServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannedCategoriesViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _plannedCategories: MutableLiveData<Resource<List<Category>>> = MutableLiveData()
    val plannedCategories: LiveData<Resource<List<Category>>>
        get() = _plannedCategories

    init {
        // todo(remove hardcode and add actual date)
        getPlannedCategories()
    }

    fun getPlannedCategories() = viewModelScope.launch {
        _plannedCategories.value = Resource.Loading

        delay(1000)
        _plannedCategories.value = Resource.Success(testData)

        // todo (uncomment when api will be ready)
        //_plannedCategories.value = repository.getPlannedCategories()
    }

    private val testData: List<Category> = listOf(
        Category("Счетчики", 1, ""),
        Category("Вода", 2, ""),
        Category("Газ", 3, ""),
        Category("Плита", 4, ""),
        Category("Двор", 5, "")
    )
}