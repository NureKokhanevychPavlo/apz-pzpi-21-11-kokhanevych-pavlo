package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class GetCurrentRentingUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(userId: Int): Flow<List<HistoryRentingResponse>> {
        return withContext(Dispatchers.IO) {
            userSource.getHistoryRenting(userId)
                .map { historyList ->
                    historyList.filter {
                        it.beginDate.isBefore(LocalDateTime.now()) && LocalDateTime.now()
                            .isBefore(it.endDate)
                    }
                }
        }
    }
}