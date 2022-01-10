package com.example.officemanagerapp.models

data class NetworkPOrder(
    val type: String,
    val day: String,
    val timeStart: String,
    val timeEnd: String,
    val address: String,
    val floor: String,
    val room: String,
    val status: String,
    val rate: Float?,
    val review: String?
) {
    val time: String
        get() = "$timeStart - $timeEnd"
}

fun NetworkPOrder.toOrder(): OrderRVItem.Order {
    return OrderRVItem.Order(
        type = type,
        day = day,
        timeStart = timeStart,
        timeEnd = timeEnd,
        address = address,
        floor = floor,
        room = room,
        company = null,
        price = null,
        status = status,
        rate = rate,
        review = review,
        orderType = OrderType.PLANNED
    )
}

fun List<NetworkPOrder>.toOrder(): List<OrderRVItem.Order> {
    return map {
        OrderRVItem.Order(
            type = it.type,
            day = it.day,
            timeStart = it.timeStart,
            timeEnd = it.timeEnd,
            address = it.address,
            floor = it.floor,
            room = it.room,
            company = null,
            price = null,
            status = it.status,
            rate = it.rate,
            review = it.review,
            orderType = OrderType.PLANNED
        )
    }
}