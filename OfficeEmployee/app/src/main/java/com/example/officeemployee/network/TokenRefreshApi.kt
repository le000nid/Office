package com.example.officeemployee.network

import com.example.officeemployee.responses.LoginResponse
import retrofit2.http.POST

interface TokenRefreshApi : BaseApi {

    @POST("auth/refresh")
    suspend fun refreshAccessToken(): LoginResponse
}