package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRoomsHotelUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(userId: Int): Flow<List<Room>> {
        return withContext(Dispatchers.IO) {
            userSource.getRoomsByHotel(userId)
        }
    }
}