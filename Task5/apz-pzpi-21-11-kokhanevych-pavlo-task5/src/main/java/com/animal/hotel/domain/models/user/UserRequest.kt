package com.animal.hotel.domain.models.user

import okhttp3.MultipartBody

data class UserRequest(
    val user: User,

    val multipartFile: MultipartBody.Part?
)
