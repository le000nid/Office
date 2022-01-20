package com.example.officemanagerapp.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.*
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.OrdersRepository
import com.example.officemanagerapp.util.ORDER_ACTIVE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersDetailedViewModel @Inject constructor(
    private val repository: OrdersRepository
): ViewModel() {

    private val _orderItemLiveData = MutableLiveData<Resource<OrderRVItem.Order>>()
    val orderItemLiveData: LiveData<Resource<OrderRVItem.Order>>
        get() = _orderItemLiveData

    fun getOrderItem(orderId: Int) = viewModelScope.launch {
        _orderItemLiveData.postValue(Resource.Loading)

        //val order = repository.getOrderById(orderId)
        delay(1000)

        val company = CompanyShort("Twitter", "https://www.google.com/search?q=twitter&sxsrf=AOaemvIGf6QaASmf6onUHSVvbCDw9x1lBg:1641741876972&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjixo6L_aT1AhXhmIsKHf6RBEkQ_AUoAnoECAIQBA&biw=1535&bih=762&dpr=1.25#imgrc=y_6Z3dmjEp2YHM", 4.25f, 24)
        val order = OrderRVItem.Order("Уборка офиса", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123",  company, 1000, ORDER_ACTIVE, 4f ,"Обратился за помощью к мастеру, все было велеиколепно, мастер справился прекрасно", OrderType.MARKET)

        _orderItemLiveData.value = Resource.Success(order)
    }

    private val _reviewPutResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val reviewPutResponse: LiveData<Resource<Boolean>>
        get() = _reviewPutResponse

    fun putReviewOrder(order: OrderRVItem.Order) = viewModelScope.launch {
        _reviewPutResponse.value = Resource.Loading
        delay(1000)
        //_reviewPutResponse.value = repository.putPlannedOrder(order)
        _reviewPutResponse.value = Resource.Success(true)
    }
}