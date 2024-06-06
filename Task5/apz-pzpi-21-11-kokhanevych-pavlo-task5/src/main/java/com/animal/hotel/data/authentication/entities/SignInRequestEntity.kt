package com.animal.hotel.data.authentication.entities

import com.animal.hotel.domain.models.authentication.SignInRequest

data class SignInRequestEntity(
    val email: String,
    val password: String
){
    fun toSignInRequest(): SignInRequest {
        return SignInRequest(email, password)
    }

    companion object {
        fun toSignInRequestEntity(request: SignInRequest) = SignInRequestEntity (
            request.email,
            request.password
        )
    }
}
