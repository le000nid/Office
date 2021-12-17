package com.example.officeemployee.models

data class WorkerDay (
    val day: String,
    val type: Int,
    val timesList: List<WorkerTime>?)