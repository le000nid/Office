package com.example.officeemployee.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.officeemployee.database.CacheDao
import com.example.officeemployee.database.asDomainMarketOrder
import com.example.officeemployee.database.asDomainPlannedOrder
import com.example.officeemployee.models.*
import com.example.officeemployee.network.OrderApi
import com.example.officeemployee.network.SafeApiCall
import com.example.officeemployee.responses.OrderUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: OrderApi
): SafeApiCall {

    val activePlannedOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getPlannedOrders(0)) {
            it.asDomainPlannedOrder()
        }

    val historyPlannedOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getPlannedOrders(1)) {
            it.asDomainPlannedOrder()
        }

    suspend fun getPlannedOrders() = safeApiCall { api.getPlannedOrders() }

    suspend fun insertAllPlannedOrdersToCache(orders: List<NetworkOrder>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllPlannedOrders(*orders.asCachePlannedModel())
        }
    }

    suspend fun putPlannedOrder(order: Order) = safeApiCall {
        val userRate = OrderUpdate("userRate", order.userRate.toString())
        val userReview = OrderUpdate("userReview", order.userReview)
        val container = listOf(userRate, userReview)

        api.updatePlannedOrder(order.id, container)
    }

    suspend fun postPlannedOrder(plannedOrder: PlannedOrderPost) = safeApiCall {
        api.postPlannedOrder(plannedOrder)
    }



    val activeMarketOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getMarketOrders(0)) {
            it.asDomainMarketOrder()
        }

    val historyMarketOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getMarketOrders(1)) {
            it.asDomainMarketOrder()
        }

    suspend fun getMarketOrders() = safeApiCall { api.getMarketOrders() }

    suspend fun insertAllMarketOrdersToCache(orders: List<NetworkOrder>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllMarketOrders(*orders.asCacheMarketModel())
        }
    }

    suspend fun putMarketOrder(order: Order) = safeApiCall {
        val userRate = OrderUpdate("userRate", order.userRate.toString())
        val userReview = OrderUpdate("userReview", order.userReview)
        val container = listOf(userRate, userReview)

        api.updateMarketOrder(order.id, container)
    }

    suspend fun postMarketOrder(marketOrder: MarketOrderPost) = safeApiCall {
        api.postMarketOrder(marketOrder)
    }

    suspend fun getWorkerCalendarById(workerId: Int) = safeApiCall {
        api.getWorkerCalendarById(workerId)
    }

    suspend fun getWorkerCalendar(subcategoryId: Int) = safeApiCall {
        api.getWorkerCalendar(subcategoryId)
    }
}