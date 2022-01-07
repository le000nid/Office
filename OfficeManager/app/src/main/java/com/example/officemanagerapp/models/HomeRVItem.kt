package com.example.officemanagerapp.models

import android.os.Parcelable
import com.example.officemanagerapp.util.smartTruncate
import kotlinx.android.parcel.Parcelize

sealed class HomeRVItem {

    data class Alert(
        val id: Int,
        val date: String,
        val title: String,
        val body: String
    ): HomeRVItem()

    @Parcelize
    data class NewsItem(
        val title: String,
        val description: String,
        val photoUrl: String,
        val createdAt: String,
    ): HomeRVItem(), Parcelable {
        val shortDescription: String
            get() = description.smartTruncate(200)
    }

    data class HomeCard(
        val title: String,
        val drawableUrl: String
    ): HomeRVItem()

    data class Title(
        val title: String
    ): HomeRVItem()
}
