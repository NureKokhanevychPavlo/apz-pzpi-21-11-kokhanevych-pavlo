package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTimeScheduleUseCase @Inject constructor(
    private val userSource: UserSource
) {
    suspend operator fun invoke(listOfResponseSchedule: List<ScheduleFeedResponse>) {
        withContext(Dispatchers.IO) {
            userSource.updateTimeOfScheduleFeed(listOfResponseSchedule)
        }
    }
}