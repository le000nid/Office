package com.example.officeemployee.network

import com.example.officeemployee.responses.UserResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("profile")
    suspend fun getUser(): UserResponse
}