package com.example.officeworkerapp.network

import com.example.officeworkerapp.responses.AuthUser
import com.example.officeworkerapp.responses.LoginResponse
import retrofit2.http.*

interface AuthApi: BaseApi {

    @POST("/auth/login")
    suspend fun login(@Body authUser: AuthUser): LoginResponse
}