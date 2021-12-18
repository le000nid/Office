package com.example.officemanagerapp.network

import com.example.officemanagerapp.models.NetworkAlert
import com.example.officemanagerapp.models.NetworkNewsItem
import retrofit2.http.GET

interface HomeApi {

    @GET("/news")
    suspend fun getNews(): List<NetworkNewsItem>

    @GET("/announcements")
    suspend fun getAlerts(): List<NetworkAlert>
}