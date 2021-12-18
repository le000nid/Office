package com.example.officemanagerapp.responses

data class AuthUser(
    val businessAccount: String,
    val password: String,
    val firebaseToken: String
    )