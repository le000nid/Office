package com.example.officeworkerapp.network

import com.example.officeworkerapp.responses.UserResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("profile")
    suspend fun getUser(): UserResponse
}