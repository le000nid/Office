package com.example.officeemployee.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.officeemployee.database.CacheDao
import com.example.officeemployee.database.asDomainAlertModel
import com.example.officeemployee.database.asDomainModel
import com.example.officeemployee.models.*
import com.example.officeemployee.network.HomeApi
import com.example.officeemployee.network.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: HomeApi
): SafeApiCall {

    val newsItems: LiveData<List<NewsItem>> =
        Transformations.map(cacheDao.getNewsItems()) {
            it.asDomainModel()
        }

    suspend fun getNews() = safeApiCall { api.getNews() }

    suspend fun insertAllNewsToCache(news: List<NetworkNewsItem>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllNews(*news.asCacheModel())
        }
    }


    val alerts: LiveData<List<Alert>> =
        Transformations.map(cacheDao.getAlerts()) {
            it.asDomainAlertModel()
        }

    suspend fun getAlerts() = safeApiCall { api.getAlerts() }

    suspend fun insertAllAlertsToCache(alerts: List<NetworkAlert>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllAlerts(*alerts.asCacheModel())
        }
    }

    suspend fun deleteAllAlerts() {
        withContext(Dispatchers.IO) {
            cacheDao.deleteAllAlerts()
        }
    }
}
