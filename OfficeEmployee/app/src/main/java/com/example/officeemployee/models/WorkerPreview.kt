package com.example.officeemployee.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkerPreview (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photo: String,
    val averageCost: String,
    var rate: Double,
    val experience: String,
): Parcelable {
    val name: String
        get() = "$firstName $lastName"
}