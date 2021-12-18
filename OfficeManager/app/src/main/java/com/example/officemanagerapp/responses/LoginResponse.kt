package com.example.officemanagerapp.responses

data class LoginResponse(
    val access: TokenResponse,
    val refresh: TokenResponse
)