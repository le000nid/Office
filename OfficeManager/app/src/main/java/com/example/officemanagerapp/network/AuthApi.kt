package com.example.officemanagerapp.network

import com.example.officemanagerapp.responses.AuthUser
import com.example.officemanagerapp.responses.LoginResponse
import retrofit2.http.*

interface AuthApi: BaseApi {

    @POST("/auth/login")
    suspend fun login(@Body authUser: AuthUser): LoginResponse
}