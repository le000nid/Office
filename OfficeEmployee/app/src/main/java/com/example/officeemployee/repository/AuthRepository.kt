package com.example.officeemployee.repository

import com.example.officeemployee.database.UserPreferences
import com.example.officeemployee.network.AuthApi
import com.example.officeemployee.responses.AuthUser
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(authUser: AuthUser) = safeApiCall {
        api.login(authUser)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveTokens(accessToken, refreshToken)
    }
}