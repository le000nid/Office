package com.example.officeworkerapp.responses

data class AuthUser(
    val businessAccount: String,
    val password: String,
    val firebaseToken: String
    )