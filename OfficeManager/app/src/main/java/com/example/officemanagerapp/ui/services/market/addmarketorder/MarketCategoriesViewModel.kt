package com.example.officemanagerapp.ui.services.market.addmarketorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.Category
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.ServicesRepository
import com.example.officemanagerapp.responses.SectionCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketCategoriesViewModel @Inject constructor(
    private val repository: ServicesRepository
) : ViewModel() {

    private val _marketCategories: MutableLiveData<Resource<List<SectionCategories>>> = MutableLiveData()
    val marketCategories: LiveData<Resource<List<SectionCategories>>>
        get() = _marketCategories

    fun getMarketCategories() = viewModelScope.launch {
        _marketCategories.value = Resource.Loading
        _marketCategories.value = repository.getMarketCategories()
    }

    val categoriesLists: List<SectionCategories> = listOf(
        SectionCategories("Популярное", listOf(
                Category("Помощь с пк", 5, ""),
                Category("Помощь с ремонтом", 5, ""),
                Category("Няня на час", 5, ""),
                Category("Помощь с уборкой", 5, ""),
                Category("Помощь с уборкой", 5, "")
            )),
        SectionCategories("Необходимое", listOf(
            Category("Помощь с пк", 5, ""),
            Category("Помощь с ремонтом", 5, ""),
            Category("Няня на час", 5, ""),
            Category("Помощь с уборкой", 5, ""),
            Category("Помощь с уборкой", 5, "")
        )),
        SectionCategories("Важное", listOf(
            Category("Помощь с пк", 5, ""),
            Category("Помощь с ремонтом", 5, ""),
            Category("Няня на час", 5, ""),
            Category("Помощь с уборкой", 5, ""),
            Category("Помощь с уборкой", 5, "")
        )),
        SectionCategories("Что-то ещё", listOf(
            Category("Помощь с пк", 5, ""),
            Category("Помощь с ремонтом", 5, ""),
            Category("Няня на час", 5, ""),
            Category("Помощь с уборкой", 5, ""),
            Category("Помощь с уборкой", 5, "")
        ))
    )

}
