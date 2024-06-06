package com.animal.hotel.domain.sources

import com.animal.hotel.domain.models.authentication.AuthenticationResponse
import com.animal.hotel.domain.models.authentication.RefreshRequest
import com.animal.hotel.domain.models.authentication.SignInRequest
import com.animal.hotel.domain.models.user.User

interface AuthenticationSource {

    suspend fun signIn(signInRequest: SignInRequest): AuthenticationResponse

    suspend fun refreshToken(refreshRequest: RefreshRequest): AuthenticationResponse

    suspend fun registerUser(userRequest: User)

    suspend fun getPresentUser(email: String): Int
}