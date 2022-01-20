package com.example.officemanagerapp.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officemanagerapp.models.*
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.repository.OrdersRepository
import com.example.officemanagerapp.util.ORDER_ACTIVE
import com.example.officemanagerapp.util.ORDER_NOT_PAID
import com.example.officemanagerapp.util.ORDER_PAID
import dagger.hilt.android.lifecycle.HiltViewModel
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
            NetworkMOrder("Уборка офиса (only rate)", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123",  company, 1000, ORDER_PAID, 4f, null),
            NetworkMOrder("Уборка офиса (both)", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123",  company, 1000, ORDER_ACTIVE, 4f ,"Обратился за помощью к мастеру, все было велеиколепно, мастер справился прекрасно")
        ))

        val pOrders = Resource.Success(listOf(
            NetworkPOrder("Уборка офиса (no both)", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123", ORDER_ACTIVE, null, null),
            NetworkPOrder("Уборка офиса (both)", "24 февраля 2021", "14:00", "15:00", "Ул. Ленина 24/5", "4", "123", ORDER_NOT_PAID, 4f, "Обратился за помощью к мастеру, все было велеиколепно, мастер справился прекрасно")
        ))

        val activeMOrders = mOrders.value.toOrder().filter { it.status == ORDER_ACTIVE }
        val activePOrders = pOrders.value.toOrder().filter { it.status == ORDER_ACTIVE }

        val activeOrders: MutableList<OrderRVItem.Order> = mutableListOf()
        activeOrders.addAll(activeMOrders)
        activeOrders.addAll(activePOrders)

        val finishedMOrders = mOrders.value.toOrder().filter { it.status != ORDER_ACTIVE }
        val finishedPOrders = pOrders.value.toOrder().filter { it.status != ORDER_ACTIVE }

        val finishedOrders: MutableList<OrderRVItem.Order> = mutableListOf()
        finishedOrders.addAll(finishedMOrders)
        finishedOrders.addAll(finishedPOrders)

        val ordersItemsList = mutableListOf<OrderRVItem>()

        // TODO(add other cases)
        if (pOrders is Resource.Success && mOrders is Resource.Success) {
            ordersItemsList.add(OrderRVItem.Title("Активные"))
            ordersItemsList.addAll(activeOrders)
            ordersItemsList.add(OrderRVItem.Title("Завершенные"))
            ordersItemsList.addAll(finishedOrders)

            _ordersListItemsLiveData.postValue(Resource.Success(ordersItemsList))
        } else {
            Resource.Failure(false, null, null)
        }
    }
}