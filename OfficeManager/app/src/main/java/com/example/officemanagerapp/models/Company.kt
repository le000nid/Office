package com.example.officemanagerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val title: String,
    val drawableUrl: String,
    val rate: Float,
    val countRate: Int,
    val info: String,
    val averagePrice: String,
    val reviewsList: List<Review>?
): Parcelable

@Parcelize
data class Review (
    val name: String,
    val drawableUrl: String,
    val rate: Float,
    val date: String,
    val text: String
): Parcelable