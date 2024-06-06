package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPetsUserUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(userId: Int): Flow<List<Pet>> {
        return withContext(Dispatchers.IO) {
            userSource.getPetsOfUser(userId)
        }
    }
}