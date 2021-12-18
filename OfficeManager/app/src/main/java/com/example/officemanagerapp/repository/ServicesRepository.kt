package com.example.officemanagerapp.repository

import com.example.officemanagerapp.network.OrderApi
import com.example.officemanagerapp.network.SafeApiCall
import javax.inject.Inject

class ServicesRepository @Inject constructor(
    private val api: OrderApi
): SafeApiCall {

    suspend fun getPlannedCategories() = safeApiCall { api.getPlannedCategories() }

    suspend fun getMarketCategories() = safeApiCall { api.getMarketCategories() }

    suspend fun getMarketPreviewWorkers() = safeApiCall { api.getMarketPreviewWorkers(1) }

    suspend fun getMarketWorker() = safeApiCall { api.getMarketWorker(1) }
}