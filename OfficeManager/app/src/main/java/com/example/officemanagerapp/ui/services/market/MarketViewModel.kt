package com.example.officemanagerapp.ui.services.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.Order
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val repository: OrdersRepository
): ViewModel() {


    /**
     * Network get request. All market orders
     */
    private val _marketOrders: MutableLiveData<Resource<List<Order>>> = MutableLiveData()
    val marketOrders: LiveData<Resource<List<Order>>>
        get() = _marketOrders

    fun getMarketOrders() = viewModelScope.launch {
        _marketOrders.value = Resource.Loading
        _marketOrders.value = repository.getMarketOrders()
    }


    private val _marketPutResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val marketPutResponse: LiveData<Resource<Boolean>>
        get() = _marketPutResponse

    fun putMarketOrder(order: Order) = viewModelScope.launch {
        _marketPutResponse.value = Resource.Loading
        //_marketPutResponse.value = repository.putMarketOrder(order)
    }
}