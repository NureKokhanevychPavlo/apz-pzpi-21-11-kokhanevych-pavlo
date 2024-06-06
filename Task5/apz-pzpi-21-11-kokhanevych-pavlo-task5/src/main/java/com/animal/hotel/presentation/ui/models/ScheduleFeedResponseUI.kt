package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.utils.enums.FoodType
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class ScheduleFeedResponseUI(
    val scheduleId: Int,
    val dateTime: LocalDateTime,
    val portion: Float,
    val typeFood: FoodType
): Parcelable {

    fun toScheduleFeedResponse(): ScheduleFeedResponse {
        return ScheduleFeedResponse(scheduleId, dateTime, portion, typeFood)
    }

    companion object {
        fun toScheduleFeedResponseUI(scheduleFeedResponse: ScheduleFeedResponse) = ScheduleFeedResponseUI (
            scheduleFeedResponse.scheduleId,
            scheduleFeedResponse.dateTime,
            scheduleFeedResponse.portion,
            scheduleFeedResponse.typeFood
        )
    }
}