package com.animal.hotel.domain.models.user

import com.animal.hotel.utils.enums.UserType
import java.time.LocalDate

data class User(
    val userId: Int = 0,
    val fullName: String,
    val password: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val typeUser: UserType,
    var photoLink: String? = null
)
