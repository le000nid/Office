package com.example.officeemployee.network

import com.example.officeemployee.models.NetworkAlert
import com.example.officeemployee.models.NetworkNewsItem
import retrofit2.http.GET

interface HomeApi {

    @GET("/news")
    suspend fun getNews(): List<NetworkNewsItem>

    @GET("/announcements")
    suspend fun getAlerts(): List<NetworkAlert>
}