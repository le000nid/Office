package com.example.officemanagerapp.ui.services.base

import com.example.officemanagerapp.models.Order

class ReviewClick(val block: (Order) -> Unit) {
    fun onReviewClick(order: Order) = block(order)
}