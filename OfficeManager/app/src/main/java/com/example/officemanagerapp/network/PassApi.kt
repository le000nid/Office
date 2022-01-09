package com.example.officemanagerapp.network

import com.example.officemanagerapp.models.Pass
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface PassApi {

    @GET("/passes")
    suspend fun getPasses(@Query("date") date: Date): List<Pass>

    @POST("/passes")
    suspend fun postPass(@Body pass: Pass): Pass
}