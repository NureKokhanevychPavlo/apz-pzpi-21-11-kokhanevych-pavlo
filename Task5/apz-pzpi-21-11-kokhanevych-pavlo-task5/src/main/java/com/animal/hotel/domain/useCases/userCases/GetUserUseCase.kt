package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(email: String): User {
        return withContext(Dispatchers.IO) {
            userSource.getPresentUser(email)
        }
    }
}