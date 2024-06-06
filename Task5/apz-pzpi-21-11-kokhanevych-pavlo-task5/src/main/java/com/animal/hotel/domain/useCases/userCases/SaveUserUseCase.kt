package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.models.user.UserRequest
import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(user: User, photoLink: String?) {
        withContext(Dispatchers.IO) {
            userSource.saveUser(user, photoLink)
        }
    }
}