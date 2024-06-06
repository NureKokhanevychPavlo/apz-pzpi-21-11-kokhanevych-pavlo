package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetHistoryRentingUseCase @Inject constructor(
    private val userSource: UserSource
) {
    suspend operator fun invoke(userId: Int): Flow<List<HistoryRentingResponse>> {
        return withContext(Dispatchers.IO) {
            userSource.getHistoryRenting(userId)
        }
    }
}