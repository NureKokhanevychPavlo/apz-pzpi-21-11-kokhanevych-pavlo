package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.Hotel
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllHotelsUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(): Flow<List<Hotel>> {
        return withContext(Dispatchers.IO) {
            userSource.getAllHotels()
        }
    }
}