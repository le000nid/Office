package com.example.officemanagerapp.network

import com.example.officemanagerapp.models.Pass
import retrofit2.http.GET
import retrofit2.http.Query

interface PassApi {

    @GET("/passes")
    suspend fun getPasses(@Query("date") date: String): List<Pass>
}