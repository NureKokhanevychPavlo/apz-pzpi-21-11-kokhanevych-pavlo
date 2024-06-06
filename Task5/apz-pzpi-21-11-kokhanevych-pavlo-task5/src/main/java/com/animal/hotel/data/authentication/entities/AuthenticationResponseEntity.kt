package com.animal.hotel.data.authentication.entities

import com.animal.hotel.domain.models.authentication.AuthenticationResponse
import com.google.gson.annotations.SerializedName

data class AuthenticationResponseEntity(
    val token: String,
    @SerializedName("refresh_token") val refreshToken: String
) {
    fun toAuthenticationResponse(): AuthenticationResponse {
        return AuthenticationResponse(token, refreshToken)
    }

    companion object {
        fun toAuthenticationResponseEntity(response: AuthenticationResponse) = AuthenticationResponseEntity (
            response.token,
            response.refreshToken
        )
    }
}
