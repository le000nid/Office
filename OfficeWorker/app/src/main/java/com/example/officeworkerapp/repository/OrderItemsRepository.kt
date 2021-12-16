package com.example.officeworkerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.officeworkerapp.database.CacheDao
import com.example.officeworkerapp.database.asDomainOrder
import com.example.officeworkerapp.models.Order
import com.example.officeworkerapp.network.*
import com.example.officeworkerapp.responses.asCacheModel
import javax.inject.Inject


class OrderItemsRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: OrderApi
): SafeApiCall {

    fun getOrderItems(getData: String) : LiveData<List<Order>> {
        val orderItems: LiveData<List<Order>> =
            Transformations.map(cacheDao.getOrderItems()) {
                it.asDomainOrder()
            }
        return orderItems
    }

    suspend fun saveOrderItem(itemOrder: NetworkOrderItem){
        cacheDao.insertAllOrders(NetworkOrder(itemOrder).asDatabaseModel())
    }

    suspend fun refreshOrderItems() = safeApiCall{
//        val orders = api.getPlannedOrders()
//        cacheDao.insertAllOrders(*orders.asCacheModel())
    }
}