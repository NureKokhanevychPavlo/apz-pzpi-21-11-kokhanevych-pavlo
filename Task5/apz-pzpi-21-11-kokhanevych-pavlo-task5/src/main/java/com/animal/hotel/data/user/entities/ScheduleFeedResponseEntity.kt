package com.animal.hotel.data.user.entities

import com.animal.hotel.utils.enums.FoodType
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ScheduleFeedResponseEntity(
    val scheduleId: Int,
    val dateTime: LocalDateTime,
    val portion: Float,
    val typeFood: FoodType
) {
    fun toScheduleFeedResponse(): ScheduleFeedResponse {
        return ScheduleFeedResponse(scheduleId, dateTime, portion, typeFood)
    }

    companion object {
        fun toScheduleFeedResponseEntity(response: ScheduleFeedResponse) = ScheduleFeedResponseEntity (
            response.scheduleId,
            response.dateTime,
            response.portion,
            response.typeFood
        )
    }
}
