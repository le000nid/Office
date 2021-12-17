package com.example.officeemployee.repository

import com.example.officeemployee.network.BaseApi
import com.example.officeemployee.network.SafeApiCall


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall { api.logout() }
}