package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.Hotel

data class HotelEntity(
    val hotelId: Int,
    val name: String,
    val region: String,
    val district: String,
    val city: String,
    val street: String,
    val numberHouse: String
){
    fun toHotel(): Hotel {
        return Hotel(hotelId, name, region, district, city, street, numberHouse)
    }

    companion object {
        fun toHotelEntity(hotel: Hotel) = HotelEntity (
            hotel.hotelId,
            hotel.name,
            hotel.region,
            hotel.district,
            hotel.city,
            hotel.street,
            hotel.numberHouse
        )
    }
}
