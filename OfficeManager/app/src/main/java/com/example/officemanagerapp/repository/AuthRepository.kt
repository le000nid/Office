package com.example.officemanagerapp.repository

import com.example.officemanagerapp.database.UserPreferences
import com.example.officemanagerapp.network.AuthApi
import com.example.officemanagerapp.responses.AuthUser
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