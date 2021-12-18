package com.example.officemanagerapp.models

data class WorkerDay (
    val day: String,
    val type: Int,
    val timesList: List<WorkerTime>?)