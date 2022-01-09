package com.example.officemanagerapp.models

import java.util.*

data class Pass (
    val id: Int,
    val fullName: String?,
    val phone: String?,
    val dateStart: String?,
    val dateEnd: String?
) {
    val fullDate: String = "$dateStart - $dateEnd"
}