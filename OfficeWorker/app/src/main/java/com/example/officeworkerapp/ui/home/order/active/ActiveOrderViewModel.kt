package com.example.officeworkerapp.ui.home.order.active

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.officeworkerapp.models.Order
import com.example.officeworkerapp.models.Photo
import com.example.officeworkerapp.network.NetworkOrderItem
import com.example.officeworkerapp.repository.OrderItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActiveOrderViewModel @Inject constructor(
    private val repository: OrderItemsRepository,
): ViewModel() {

    private val _completePhotos = MutableLiveData<List<Photo>>(listOf())

    private val _doorPhotos = MutableLiveData<List<Photo>>(listOf())

    val completePhotos: MutableLiveData<List<Photo>>
        get() = _completePhotos

    val doorPhotos: MutableLiveData<List<Photo>>
        get() = _doorPhotos

    suspend fun saveOrderItem(itemOrder: Order) {
        val order = NetworkOrderItem(itemOrder.date, itemOrder.address, itemOrder.time, itemOrder.problem, itemOrder.status, itemOrder.id)
        repository.saveOrderItem(order)
    }


}