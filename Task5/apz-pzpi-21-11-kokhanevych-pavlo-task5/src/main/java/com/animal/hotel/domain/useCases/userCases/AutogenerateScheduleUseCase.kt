package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.AutogenerateRequest
import com.animal.hotel.domain.models.user.Diet
import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AutogenerateScheduleUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(autogenerateRequest: AutogenerateRequest): Flow<List<Schedule>> {
        return withContext(Dispatchers.IO) {
            val authenticationResponse = userSource.autogenerateSchedule(autogenerateRequest)
            val schedules: MutableList<Schedule> = mutableListOf()
            authenticationResponse.first().map {
                val diet = Diet(
                    portion = it.portion,
                    typeFood = it.typeFood,
                    rent = autogenerateRequest.rent
                )

                val schedule = Schedule(
                    dateTime = it.dateTime,
                    diet = diet
                )

                schedules.add(schedule)
            }
            flowOf(schedules.toList())
        }
    }
}