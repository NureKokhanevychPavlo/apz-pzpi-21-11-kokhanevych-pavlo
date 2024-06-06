package com.animal.hotel.domain.models.user

import java.time.LocalDateTime

data class DateRequest(
    var beginDate: LocalDateTime,
    var endDate: LocalDateTime
)
