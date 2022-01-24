package com.example.officemanagerapp.network

import com.example.officemanagerapp.models.Tenant
import retrofit2.http.GET

interface TenantsApi {

    @GET("/passes")
    suspend fun getTenants(): List<Tenant>
}