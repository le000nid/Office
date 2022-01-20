package com.example.officemanagerapp.network

import com.example.officemanagerapp.models.*
import com.example.officemanagerapp.responses.*
import retrofit2.http.*

interface OrderApi {

    // ---------- Planned orders ----------

    @POST("/planned-orders")
    suspend fun postPlannedOrder(@Body order: Order): Order

    @GET("/planned-orders")
    suspend fun getPlannedOrders(): List<NetworkPOrder>

    @PUT("/planned-orders/{id}")
    suspend fun updatePlannedOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

    @GET("/planned-categories")
    suspend fun getPlannedCategories(): List<Category>



    // ---------- Market orders ----------

    @POST("/marked-orders")
    suspend fun postMarketOrder(@Body plannedOrder: Order): Order

    @GET("/marked-orders")
    suspend fun getMarketOrders(): List<NetworkMOrder>

    @PUT("/marked-orders/{id}")
    suspend fun updateMarketOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

    @GET("/market-categories")
    suspend fun getMarketCategories(): List<SectionCategories>




    @GET("/order")
    suspend fun getOrderById(orderId: Int): OrderRVItem.Order

    @GET("/company")
    suspend fun getCompanies(id: Int): List<Company>
}