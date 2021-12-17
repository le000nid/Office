package com.example.officeemployee.network

import com.example.officeemployee.responses.AuthUser
import com.example.officeemployee.responses.LoginResponse
import retrofit2.http.*

interface AuthApi: BaseApi {

    @POST("/auth/login")
    suspend fun login(@Body authUser: AuthUser): LoginResponse
}