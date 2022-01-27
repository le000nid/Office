package com.example.officemanagerapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.*
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<HomeRVItem>>>()
    val homeListItemsLiveData: LiveData<Resource<List<HomeRVItem>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    fun getHomeListItems() = viewModelScope.launch {
        _homeListItemsLiveData.postValue(Resource.Loading)

        val newsDeferred = async { repository.getNews() }
        val alertsDeferred = async { repository.getAlerts() }

        val news = newsDeferred.await()
        val alerts = alertsDeferred.await()

        val homeCards = listOf(
            HomeRVItem.HomeCard("Выписать пропуск", ""),
            HomeRVItem.HomeCard("Заказать мастера", ""),
            HomeRVItem.HomeCard("Маркет услуг", ""),
            HomeRVItem.HomeCard("Возникла проблема", ""),
        )

        val homeItemsList = mutableListOf<HomeRVItem>()

        if (news is Resource.Success && alerts is Resource.Success && news.value.isNotEmpty() && alerts.value.isNotEmpty()) {
            homeItemsList.addAll(alerts.value)

            homeItemsList.add(HomeRVItem.Title("Основное"))
            homeItemsList.addAll(homeCards)

            homeItemsList.add(HomeRVItem.Title("Обратите внимание"))
            homeItemsList.addAll(news.value)

            _homeListItemsLiveData.postValue(Resource.Success(homeItemsList))
        } else if (news is Resource.Success && news.value.isNotEmpty()) {
            homeItemsList.add(HomeRVItem.Title("Основное"))
            homeItemsList.addAll(homeCards)

            homeItemsList.add(HomeRVItem.Title("Обратите внимание"))
            homeItemsList.addAll(news.value)
        } else if (alerts is Resource.Success && alerts.value.isNotEmpty()) {
            homeItemsList.addAll(alerts.value)

            homeItemsList.add(HomeRVItem.Title("Основное"))
            homeItemsList.addAll(homeCards)
        } else {
            Resource.Failure(false, null, null)
        }
    }
}
