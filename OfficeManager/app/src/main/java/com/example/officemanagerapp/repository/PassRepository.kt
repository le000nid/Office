package com.example.officemanagerapp.repository

import com.example.officemanagerapp.network.PassApi
import com.example.officemanagerapp.network.SafeApiCall
import javax.inject.Inject

class PassRepository @Inject constructor(
    private val api: PassApi
): SafeApiCall {
    suspend fun getPasses(date: String) = safeApiCall { api.getPasses(date) }
}