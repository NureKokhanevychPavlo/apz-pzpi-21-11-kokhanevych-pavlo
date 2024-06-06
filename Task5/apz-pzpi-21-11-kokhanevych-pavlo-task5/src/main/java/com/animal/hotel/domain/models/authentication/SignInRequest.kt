package com.animal.hotel.domain.models.authentication

data class SignInRequest(
    val email: String,
    val password: String
)
