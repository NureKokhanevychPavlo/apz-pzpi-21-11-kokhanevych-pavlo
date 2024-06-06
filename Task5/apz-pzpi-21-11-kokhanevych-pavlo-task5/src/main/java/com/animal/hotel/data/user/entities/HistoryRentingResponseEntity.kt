package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.HistoryRentingResponse
import java.time.LocalDateTime

data class HistoryRentingResponseEntity(
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    val petName: String,
    val roomId: Int,
    val rentId: Int,
    val numberRoom: Int,
    val hotelName: String,
    val totalPayment: Int
) {
    fun toHistoryRentingResponse(): HistoryRentingResponse {
        return HistoryRentingResponse(
            beginDate,
            endDate,
            petName,
            roomId,
            rentId,
            numberRoom,
            hotelName,
            totalPayment
        )
    }

    companion object {
        fun toHistoryRentingResponseEntity(response: HistoryRentingResponse) =
            HistoryRentingResponseEntity(
                response.beginDate,
                response.endDate,
                response.petName,
                response.roomId,
                rentId = response.rentId,
                response.numberRoom,
                response.hotelName,
                response.totalPayment
            )
    }
}
