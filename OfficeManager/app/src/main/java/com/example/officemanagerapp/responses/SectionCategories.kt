package com.example.officemanagerapp.responses

import com.example.officemanagerapp.models.CategoriesList

data class SectionCategories (
    val title: String,
    val categories: List<CategoriesList>
)