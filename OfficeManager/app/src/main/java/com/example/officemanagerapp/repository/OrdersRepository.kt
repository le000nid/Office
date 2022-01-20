package com.example.officemanagerapp.repository

import com.example.officemanagerapp.models.Order
import com.example.officemanagerapp.models.OrderRVItem
import com.example.officemanagerapp.network.OrderApi
import com.example.officemanagerapp.network.SafeApiCall
import com.example.officemanagerapp.responses.OrderUpdate
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val api: OrderApi
): SafeApiCall {


    suspend fun getPlannedOrders() = safeApiCall { api.getPlannedOrders() }


    suspend fun putPlannedOrder(order: Order) = safeApiCall {
       /* val userRate = OrderUpdate("userRate", order.userRate.toString())
        val userReview = OrderUpdate("userReview", order.userReview)
        val container = listOf(userRate, userReview)

        api.updatePlannedOrder(order.id, container)*/
    }

    suspend fun postPlannedOrder(order: Order) = safeApiCall {
        api.postPlannedOrder(order)
    }


    suspend fun getMarketOrders() = safeApiCall { api.getMarketOrders() }

    suspend fun putMarketOrder(order: Order) = safeApiCall {
        //val userRate = OrderUpdate("userRate", order.userRate.toString())
        //val userReview = OrderUpdate("userReview", order.userReview)
        //val container = listOf(userRate, userReview)

        //api.updateMarketOrder(order.id, container)
    }

    suspend fun postMarketOrder(order: Order) = safeApiCall {
        api.postMarketOrder(order)
    }

    suspend fun getWorkerCalendarById(workerId: Int) = safeApiCall {
        api.getWorkerCalendarById(workerId)
    }

    suspend fun getWorkerCalendar(subcategoryId: Int) = safeApiCall {
        api.getWorkerCalendar(subcategoryId)
    }

    suspend fun getOrderById(orderId: Int) = safeApiCall {
        api.getOrderById(orderId)
    }
}