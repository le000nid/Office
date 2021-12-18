package com.example.officemanagerapp.network

import com.example.officemanagerapp.responses.UserResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("profile")
    suspend fun getUser(): UserResponse
}