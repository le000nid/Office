package com.example.officemanagerapp.models

import android.os.Parcelable
import com.example.officemanagerapp.util.smartTruncate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    val title: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String,
): Parcelable {
    val shortDescription: String
        get() = description.smartTruncate(200)
}

data class NetworkNewsItem(
    val title: String?,
    val description: String?,
    val photoUrl: String?,
    val createdAt: String?,
)