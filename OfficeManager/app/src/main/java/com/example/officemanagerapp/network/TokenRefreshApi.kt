package com.example.officemanagerapp.network

import com.example.officemanagerapp.responses.LoginResponse
import retrofit2.http.POST

interface TokenRefreshApi : BaseApi {

    @POST("auth/refresh")
    suspend fun refreshAccessToken(): LoginResponse
}