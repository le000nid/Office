package com.example.officeworkerapp.responses

data class LoginResponse(
    val access: TokenResponse,
    val refresh: TokenResponse
)