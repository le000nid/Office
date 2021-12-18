package com.example.officemanagerapp.ui.services.base

import com.example.officemanagerapp.models.Order

class OrderClick(val block: (Order) -> Unit) {
    fun onClick(order: Order) = block(order)
}