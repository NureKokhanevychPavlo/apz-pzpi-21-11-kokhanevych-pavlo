package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.Schedule
import java.time.LocalDateTime

data class ScheduleEntity(
     val scheduleId: Int,
     val dateTime: LocalDateTime,
     val diet: DietEntity
) {
    fun toSchedule(): Schedule {
        return  Schedule(scheduleId, dateTime, diet.toDiet())
    }

    companion object {
        fun toScheduleEntity(schedule: Schedule) =  ScheduleEntity (
            schedule.scheduleId,
            schedule.dateTime,
            DietEntity.toDietEntity(schedule.diet)
        )
    }
}
