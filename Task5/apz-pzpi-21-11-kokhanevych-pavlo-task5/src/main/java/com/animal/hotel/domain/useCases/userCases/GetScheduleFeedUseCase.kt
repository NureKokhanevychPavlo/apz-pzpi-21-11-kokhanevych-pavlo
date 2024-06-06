package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetScheduleFeedUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(rentId: Int): List<ScheduleFeedResponse> {
        return withContext(Dispatchers.IO) {
            userSource.getScheduleFeed(rentId)
        }
    }
}