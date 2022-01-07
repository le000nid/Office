package com.example.officemanagerapp.repository

import com.example.officemanagerapp.network.HomeApi
import com.example.officemanagerapp.network.SafeApiCall
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: HomeApi
): SafeApiCall {

    suspend fun getNews() = safeApiCall { api.getNews() }
    suspend fun getAlerts() = safeApiCall { api.getAlerts() }
}
