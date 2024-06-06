package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.Rent
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class RentEntity(
    val rentId: Int,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    val room: RoomEntity,
    val pet: PetEntity
) {
    fun toRent(): Rent {
        return  Rent(rentId, beginDate, endDate, room.toRoom(), pet.toPet())
    }

    companion object {
        fun toRentEntity(rent: Rent) =  RentEntity (
            rent.rentId,
            rent.beginDate,
            rent.endDate,
            RoomEntity.toRoomEntity(rent.room),
            PetEntity.toPetEntity(rent.pet)
        )
    }
}
