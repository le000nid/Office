package com.example.officemanagerapp.network

import com.example.officemanagerapp.models.HomeRVItem
import retrofit2.http.GET

interface HomeApi {

    @GET("/news")
    suspend fun getNews(): List<HomeRVItem.NewsItem>

    @GET("/announcements")
    suspend fun getAlerts(): List<HomeRVItem.Alert>
}