package com.example.officeworkerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PlannedOrder(
    val id: Int,
    val title: String,
    val date: String,
    val status: Int,
)