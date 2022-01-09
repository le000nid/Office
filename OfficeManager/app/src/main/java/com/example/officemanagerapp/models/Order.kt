package com.example.officemanagerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime

@Parcelize
data class Order (
    val fullName: String? = null,
    val phone: String? = null,
    val comment: String? = null,
    val address: String? = null,
    val floor: String? = null,
    val room: String? = null,
    val companyId: Int? = null,
    val categoryId: Int
) : Parcelable