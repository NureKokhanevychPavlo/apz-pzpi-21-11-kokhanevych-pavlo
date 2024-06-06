package com.animal.hotel.domain.models.user

data class Hotel(
    val hotelId: Int,
    var name: String,
    val region: String,
    val district: String,
    val city: String,
    val street: String,
    val numberHouse: String
)
