package com.example.officemanagerapp.repository

import com.example.officemanagerapp.network.BaseApi
import com.example.officemanagerapp.network.SafeApiCall


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall { api.logout() }
}