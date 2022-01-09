package com.example.officemanagerapp.repository

import com.example.officemanagerapp.models.Pass
import com.example.officemanagerapp.network.PassApi
import com.example.officemanagerapp.network.SafeApiCall
import java.util.*
import javax.inject.Inject

class PassRepository @Inject constructor(
    private val api: PassApi
): SafeApiCall {
    suspend fun getPasses(date: Date) = safeApiCall { api.getPasses(date) }
    suspend fun postPass(pass: Pass) = safeApiCall { api.postPass(pass) }
}