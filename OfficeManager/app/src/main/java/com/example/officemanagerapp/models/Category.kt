package com.example.officemanagerapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category (
    val title: String,
    val categoryId: Int,
    @SerializedName("iconUrl") val drawableUrl: String,
): Parcelable