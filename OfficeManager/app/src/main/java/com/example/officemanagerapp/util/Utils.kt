package com.example.officemanagerapp.util

import android.app.Activity
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun formatDateToString(it: Long?): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
    return simpleDateFormat.format(it)
}

fun formatDate(time: String): OffsetDateTime {
    val res = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm"))
    val offset = OffsetDateTime.now().offset
    return res.atOffset(offset)
}