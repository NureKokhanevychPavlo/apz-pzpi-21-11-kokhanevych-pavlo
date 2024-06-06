package com.animal.hotel.domain.models.user

import java.time.LocalDateTime

data class Rent(
    val rentId: Int = 0,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    val room: Room,
    val pet: Pet
)
