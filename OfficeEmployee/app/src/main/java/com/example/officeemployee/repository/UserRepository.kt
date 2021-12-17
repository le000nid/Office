package com.example.officeemployee.repository

import com.example.officeemployee.network.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository(api) {

    suspend fun getUser() = safeApiCall { api.getUser() }

}