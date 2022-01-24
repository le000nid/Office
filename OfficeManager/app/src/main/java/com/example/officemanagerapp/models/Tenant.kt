package com.example.officemanagerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tenant (
    val title: String,
    val id: Int,
    val drawableUrl: String,
    val info: String,
): Parcelable