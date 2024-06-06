package com.animal.hotel.domain.models.user

import java.time.LocalDateTime

data class HistoryRentingResponse(
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    val petName: String,
    val roomId: Int,
    val rentId: Int,
    val numberRoom: Int,
    val hotelName: String,
    val totalPayment: Int
)
