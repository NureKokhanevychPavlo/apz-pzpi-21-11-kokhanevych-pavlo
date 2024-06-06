package com.animal.hotel.data.authentication.entities

import com.animal.hotel.domain.models.authentication.RefreshRequest

data class RefreshRequestEntity(
    val token: String
){
    fun toRefreshRequest(): RefreshRequest {
        return RefreshRequest(token)
    }

    companion object {
        fun toRefreshRequestEntity(request: RefreshRequest) = RefreshRequestEntity (
            request.token
        )
    }
}
