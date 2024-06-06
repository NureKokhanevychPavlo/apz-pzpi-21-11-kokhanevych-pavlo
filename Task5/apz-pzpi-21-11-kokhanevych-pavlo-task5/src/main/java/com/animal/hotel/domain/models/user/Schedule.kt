package com.animal.hotel.domain.models.user

import java.time.LocalDateTime

data class Schedule(
    val scheduleId: Int = 0,
    val dateTime: LocalDateTime,
    val diet: Diet
)
