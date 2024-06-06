package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddPetsUserUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(listOfPets: List<Pet>) {
        return withContext(Dispatchers.IO) {
            userSource.addPetsOfUser(listOfPets)
        }
    }
}