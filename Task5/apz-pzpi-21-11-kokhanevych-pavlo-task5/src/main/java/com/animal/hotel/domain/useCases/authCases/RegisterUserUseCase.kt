package com.animal.hotel.domain.useCases.authCases

import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.sources.AuthenticationSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authenticationSource: AuthenticationSource
) {
    suspend operator fun invoke(user: User) {
        withContext(Dispatchers.IO) {
            authenticationSource.registerUser(user)
        }
    }
}