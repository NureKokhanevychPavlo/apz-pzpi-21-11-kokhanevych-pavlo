package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import com.animal.hotel.domain.models.user.Room
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomUI (
    val roomId: Int,
    val number: Int,
    val area: Float,
    val priceHour: Float,
    val ip: String,
    val port: String,
    val hotel: HotelUI
): Parcelable {

    fun toRoom(): Room {
        return Room(roomId, number, area, priceHour, ip, port, hotel.toHotel())
    }

    companion object {
        fun toRoomUI(room: Room) = RoomUI (
            room.roomId,
            room.number,
            room.area,
            room.priceHour,
            room.ip,
            room.port,
            HotelUI.toHotelUI(room.hotel)
        )
    }
}