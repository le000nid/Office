package com.example.officemanagerapp.ui.services.order_info

import androidx.lifecycle.*
import com.example.officemanagerapp.models.Order
import com.example.officemanagerapp.models.Photo
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderInfoViewModel @Inject constructor(
    private val repository: OrdersRepository
) : ViewModel() {

    private val _photos = MutableLiveData<List<Photo>>(listOf())
    val photos: MutableLiveData<List<Photo>>
        get() = _photos

    private val _orderResponse = MutableLiveData<Resource<Order>>()
    val orderResponse: LiveData<Resource<Order>>
        get() = _orderResponse

    fun postOrder(type: OrderType) = viewModelScope.launch {
        _orderResponse.value = Resource.Loading

        if (type == OrderType.PLANNED) {
            val plannedOrder = Order(fullName.value, phone.value, comment.value, address.value, floor.value, room.value, null, categoryId.value)
            delay(1000)
            _orderResponse.value = Resource.Success(plannedOrder)
            //_orderResponse.value = repository.postPlannedOrder(plannedOrder)
        } else if (type == OrderType.MARKET) {
            val marketOrder = Order(fullName.value, phone.value, comment.value, address.value, floor.value, room.value, companyId.value, categoryId.value)
            delay(1000)
            _orderResponse.value = Resource.Success(marketOrder)
            //_orderResponse.value = repository.postMarketOrder(marketOrder)
        }
    }

    val fullName: MutableLiveData<String> = MutableLiveData()
    val phone: MutableLiveData<String> = MutableLiveData()
    val comment: MutableLiveData<String> = MutableLiveData()
    val address: MutableLiveData<String> = MutableLiveData()
    val floor: MutableLiveData<String> = MutableLiveData()
    val room: MutableLiveData<String> = MutableLiveData()
    val categoryId: MutableLiveData<Int> = MutableLiveData()
    val companyId: MutableLiveData<Int> = MutableLiveData()
}

enum class OrderType {
    PLANNED, MARKET
}

