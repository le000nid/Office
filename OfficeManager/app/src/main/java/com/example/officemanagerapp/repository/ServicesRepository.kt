package com.example.officemanagerapp.repository

import com.example.officemanagerapp.network.OrderApi
import com.example.officemanagerapp.network.SafeApiCall
import javax.inject.Inject

class ServicesRepository @Inject constructor(
    private val api: OrderApi
): SafeApiCall {

    suspend fun getPlannedCategories() = safeApiCall { api.getPlannedCategories() }

    suspend fun getMarketCategories() = safeApiCall { api.getMarketCategories() }

    suspend fun getCompanies(id: Int) = safeApiCall { api.getCompanies(id) }
}