package com.animal.hotel.domain.useCases.authCases

import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.authentication.AuthenticationResponse
import com.animal.hotel.domain.models.authentication.SignInRequest
import com.animal.hotel.domain.sources.AuthenticationSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticationSource: AuthenticationSource,
    private val appSettings: AppSettings
) {

    suspend operator fun invoke(signInRequest: SignInRequest): AuthenticationResponse {
        return withContext(Dispatchers.IO) {
            val authenticationResponse = authenticationSource.signIn(signInRequest)

            val userId = authenticationSource.getPresentUser(signInRequest.email)
            appSettings.setUserId(userId)

            authenticationResponse
        }
    }
}