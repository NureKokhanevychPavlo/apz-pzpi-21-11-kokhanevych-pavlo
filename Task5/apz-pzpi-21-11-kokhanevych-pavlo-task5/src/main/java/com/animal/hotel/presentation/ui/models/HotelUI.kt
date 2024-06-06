package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import com.animal.hotel.domain.models.user.Hotel
import kotlinx.parcelize.Parcelize

@Parcelize
data class HotelUI(
    val hotelId: Int,
    var name: String,
    val region: String,
    val district: String,
    val city: String,
    val street: String,
    val numberHouse: String
): Parcelable {

    fun toHotel(): Hotel {
        return Hotel(hotelId, name, region, district, city, street, numberHouse)
    }

    companion object {
        fun toHotelUI(hotel: Hotel) = HotelUI (
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