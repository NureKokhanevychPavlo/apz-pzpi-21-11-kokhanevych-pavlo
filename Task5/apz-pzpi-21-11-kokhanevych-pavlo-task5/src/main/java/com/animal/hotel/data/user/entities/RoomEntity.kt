package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.Room

data class RoomEntity(
     val roomId: Int,
    val number: Int,
    val area: Float,
     val priceHour: Float,
    val ip: String,
    val port: String,
    val hotel: HotelEntity
) {
    fun toRoom(): Room {
        return  Room(roomId, number, area, priceHour, ip, port, hotel.toHotel())
    }

    companion object {
        fun toRoomEntity(room: Room) =  RoomEntity (
            room.roomId,
            room.number,
            room.area,
            room.priceHour,
            room.ip,
            room.port,
            HotelEntity.toHotelEntity(room.hotel)
        )
    }
}
