package com.example.officemanagerapp.responses

import com.example.officemanagerapp.models.Category

data class SectionCategories (
    val title: String,
    val categories: List<Category>
)