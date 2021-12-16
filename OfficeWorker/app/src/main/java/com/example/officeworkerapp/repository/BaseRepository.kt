package com.example.officeworkerapp.repository

import com.example.officeworkerapp.network.BaseApi
import com.example.officeworkerapp.network.SafeApiCall


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall { api.logout() }
}