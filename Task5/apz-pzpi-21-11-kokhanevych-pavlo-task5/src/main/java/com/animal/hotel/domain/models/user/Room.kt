package com.animal.hotel.domain.models.user


data class Room(
    val roomId: Int,
    val number: Int,
    val area: Float,
    val priceHour: Float,
    val ip: String,
    val port: String,
    val hotel: Hotel
)
