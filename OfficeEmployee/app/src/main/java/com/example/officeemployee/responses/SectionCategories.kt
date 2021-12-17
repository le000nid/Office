package com.example.officeemployee.responses

import com.example.officeemployee.models.CategoriesList

data class SectionCategories (
    val title: String,
    val categories: List<CategoriesList>
)