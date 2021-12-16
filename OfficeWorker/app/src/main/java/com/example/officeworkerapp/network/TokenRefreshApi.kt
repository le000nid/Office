package com.example.officeworkerapp.network

import com.example.officeworkerapp.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TokenRefreshApi : BaseApi {

    @POST("auth/refresh")
    suspend fun refreshAccessToken(): LoginResponse
}