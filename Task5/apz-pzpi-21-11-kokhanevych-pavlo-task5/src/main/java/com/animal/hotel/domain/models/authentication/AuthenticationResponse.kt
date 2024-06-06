package com.animal.hotel.domain.models.authentication

data class AuthenticationResponse(
    val token: String,
    val refreshToken: String
)
