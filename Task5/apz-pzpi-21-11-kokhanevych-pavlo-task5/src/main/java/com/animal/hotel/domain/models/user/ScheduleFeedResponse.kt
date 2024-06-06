package com.animal.hotel.domain.models.user

import com.animal.hotel.utils.enums.FoodType
import java.time.LocalDateTime

data class ScheduleFeedResponse(
    val scheduleId: Int,
    val dateTime: LocalDateTime,
    val portion: Float,
    val typeFood: FoodType
)
