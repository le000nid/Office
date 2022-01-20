package com.example.officemanagerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class OrderRVItem {

    data class Title(
        val title: String
    ): OrderRVItem()

    @Parcelize
    data class Order(
        val type: String,
        val day: String,
        val timeStart: String,
        val timeEnd: String,
        val address: String,
        val floor: String,
        val room: String,
        val companyShort: CompanyShort?,
        val price: Int?,
        val status: String,
        val rate: Float?,
        val review: String?,
        val orderType: OrderType
    ): OrderRVItem(), Parcelable {
        val time: String
            get() = "$timeStart - $timeEnd"
    }
}

enum class OrderType {
    MARKET, PLANNED
}

@Parcelize
data class CompanyShort(
    val title: String,
    val drawableUrl: String,
    val rate: Float,
    val countRate: Int
): Parcelable
