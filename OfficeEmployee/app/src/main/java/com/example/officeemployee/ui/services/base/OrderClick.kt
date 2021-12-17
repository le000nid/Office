package com.example.officeemployee.ui.services.base

import com.example.officeemployee.models.Order

class OrderClick(val block: (Order) -> Unit) {
    fun onClick(order: Order) = block(order)
}