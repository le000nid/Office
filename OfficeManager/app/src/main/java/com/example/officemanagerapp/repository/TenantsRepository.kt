package com.example.officemanagerapp.repository

import com.example.officemanagerapp.network.SafeApiCall
import com.example.officemanagerapp.network.TenantsApi
import javax.inject.Inject

class TenantsRepository @Inject constructor(
    private val api: TenantsApi
): SafeApiCall {
    suspend fun getTenants() = safeApiCall { api.getTenants() }
}