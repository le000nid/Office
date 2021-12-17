package com.example.officeemployee.responses

data class LoginResponse(
    val access: TokenResponse,
    val refresh: TokenResponse
)