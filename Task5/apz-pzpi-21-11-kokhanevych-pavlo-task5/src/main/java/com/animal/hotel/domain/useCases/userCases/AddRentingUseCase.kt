package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddRentingUseCase @Inject constructor(
    private val userSource: UserSource
) {
    suspend operator fun invoke(listOfSchedules: List<Schedule>) {
        withContext(Dispatchers.IO) {
            userSource.addNewRentingForUser(listOfSchedules)
        }
    }
}