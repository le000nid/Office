package com.example.officemanagerapp.models


data class Alert(
    val id: Int,
    val date: String,
    val title: String,
    val body: String
)

data class NetworkAlert(
    val id: Int,
    val date: String?,
    val title: String?,
    val body: String?
)