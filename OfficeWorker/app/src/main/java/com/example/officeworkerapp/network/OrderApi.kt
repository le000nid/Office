package com.example.officeworkerapp.network

import com.example.officeworkerapp.responses.OrderListResponse
import com.example.officeworkerapp.responses.OrderUpdate
import retrofit2.http.*

interface OrderApi {

    @GET("/planned-orders")
    suspend fun getPlannedOrders(): OrderListResponse

    @PUT("/planned-orders/{id}")
    suspend fun updatePlannedOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

}