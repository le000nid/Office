package com.example.officemanagerapp.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.R
import com.example.officemanagerapp.models.Company
import com.example.officemanagerapp.models.HomeRVItem
import com.example.officemanagerapp.models.OrderRVItem
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.OrdersRepository
import com.example.officemanagerapp.util.ORDER_ACTIVE
import com.example.officemanagerapp.util.ORDER_NOT_PAID
import com.example.officemanagerapp.util.ORDER_PAID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersListViewModel @Inject constructor(
    private val repository: OrdersRepository
): ViewModel() {

    private val _ordersListItemsLiveData = MutableLiveData<Resource<List<OrderRVItem>>>()
    val ordersListItemsLiveData: LiveData<Resource<List<OrderRVItem>>>
        get() = _ordersListItemsLiveData

    init {
        getOrdersListItems()
    }

    fun getOrdersListItems() = viewModelScope.launch {
        _ordersListItemsLiveData.postValue(Resource.Loading)

        // todo(uncomment when api is ready)
        /*val pOrdersDeferred = async { repository.getPlannedOrders()}
        val mOrdersDeferred = async { repository.getMarketOrders() }

        val pOrders = pOrdersDeferred.await()
        val mOrders = mOrdersDeferred.await()*/
        val company = Company("Twitter", "https://www.google.com/search?q=twitter&sxsrf=AOaemvIGf6QaASmf6onUHSVvbCDw9x1lBg:1641741876972&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjixo6L_aT1AhXhmIsKHf6RBEkQ_AUoAnoECAIQBA&biw=1535&bih=762&dpr=1.25#imgrc=y_6Z3dmjEp2YHM", 4.25f, 24)
        val mOrders = Resource.Success(listOf(
            OrderRVItem.mOrder("Уборка офиса", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123",  company, 1000, ORDER_ACTIVE, null, null),
            OrderRVItem.mOrder("Уборка офиса", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123",  company, 1000, ORDER_ACTIVE, 4f, "Обратился за помощью к мастеру, все было велеиколепно, мастер справился прекрасно")
        ))

        val pOrders = Resource.Success(listOf(
            OrderRVItem.pOrder("Уборка офиса", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123", ORDER_PAID, null, null),
            OrderRVItem.pOrder("Уборка офиса", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123", ORDER_NOT_PAID, 4f, "Обратился за помощью к мастеру, все было велеиколепно, мастер справился прекрасно")
        ))

        val ordersItemsList = mutableListOf<OrderRVItem>()

        // TODO(add other cases)
        if (pOrders is Resource.Success && mOrders is Resource.Success) {
            ordersItemsList.add(OrderRVItem.Title("Активные"))
            ordersItemsList.addAll(mOrders.value)
            ordersItemsList.add(OrderRVItem.Title("Завершенные"))
            ordersItemsList.addAll(pOrders.value)

            _ordersListItemsLiveData.postValue(Resource.Success(ordersItemsList))
        } else {
            Resource.Failure(false, null, null)
        }
    }
}