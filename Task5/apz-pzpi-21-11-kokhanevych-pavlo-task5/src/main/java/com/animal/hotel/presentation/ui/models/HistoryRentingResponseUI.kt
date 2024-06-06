package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import java.time.LocalDateTime
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryRentingResponseUI (
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    val petName: String,
    val roomId: Int,
    val rentId: Int,
    val numberRoom: Int,
    val hotelName: String,
    val totalPayment: Int
): Parcelable {

    fun toHistoryRentingResponse(): HistoryRentingResponse {
        return HistoryRentingResponse(beginDate, endDate, petName, roomId, rentId, numberRoom, hotelName, totalPayment)
    }

    companion object {
        fun toHistoryRentingResponseUI(response: HistoryRentingResponse) = HistoryRentingResponseUI (
            response.beginDate,
            response.endDate,
            response.petName,
            response.roomId,
            response.rentId,
            response.numberRoom,
            response.hotelName,
            response.totalPayment
        )
    }
}