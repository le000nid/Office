package com.example.officemanagerapp.models

data class NetworkMOrder(
    val type: String,
    val day: String,
    val timeStart: String,
    val timeEnd: String,
    val address: String,
    val floor: String,
    val room: String,
    val companyShort: CompanyShort,
    val price: Int,
    val status: String,
    val rate: Float?,
    val review: String?
) {
    val time: String
        get() = "$timeStart - $timeEnd"
}

fun NetworkMOrder.toOrder(): OrderRVItem.Order {
    return OrderRVItem.Order(
        type = type,
        day = day,
        timeStart = timeStart,
        timeEnd = timeEnd,
        address = address,
        floor = floor,
        room = room,
        companyShort = companyShort,
        price = price,
        status = status,
        rate = rate,
        review = review,
        orderType = OrderType.MARKET
    )
}

fun List<NetworkMOrder>.toOrder(): List<OrderRVItem.Order> {
    return map {
        OrderRVItem.Order(
            type = it.type,
            day = it.day,
            timeStart = it.timeStart,
            timeEnd = it.timeEnd,
            address = it.address,
            floor = it.floor,
            room = it.room,
            companyShort = it.companyShort,
            price = it.price,
            status = it.status,
            rate = it.rate,
            review = it.review,
            orderType = OrderType.MARKET
        )
    }
}