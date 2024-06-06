package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.UserRequest
import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.http.Part

data class UserRequestEntity(
    @SerializedName("user") val userEntity: UserEntity,

    @SerializedName("multipart_file") val multipartFile: MultipartBody.Part?
) {
    fun toUserRequest(): UserRequest {
        return UserRequest(userEntity.toUser(), multipartFile)
    }

    companion object {
        fun toUserRequestEntity(userRequest: UserRequest) = UserRequestEntity (
            UserEntity.toUserEntity(userRequest.user),
            userRequest.multipartFile
        )
    }
}
