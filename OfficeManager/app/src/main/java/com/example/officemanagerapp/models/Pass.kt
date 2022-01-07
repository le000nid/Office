package com.example.officemanagerapp.models

import java.util.*

data class Pass (
    val id: Int,
    val dateStart: String,
    val dateEnd: String,
    val who: String,
    val phone: String
) {
    val fullDate: String = "$dateStart - $dateEnd"
}