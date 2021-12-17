package com.example.officeemployee.ui.services.base

import com.example.officeemployee.models.Order

class ReviewClick(val block: (Order) -> Unit) {
    fun onReviewClick(order: Order) = block(order)
}