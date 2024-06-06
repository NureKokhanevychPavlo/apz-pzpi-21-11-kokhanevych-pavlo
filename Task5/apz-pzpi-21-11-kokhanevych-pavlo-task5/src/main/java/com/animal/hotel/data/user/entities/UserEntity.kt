package com.animal.hotel.data.user.entities

import android.os.Parcelable
import com.animal.hotel.utils.enums.UserType
import com.animal.hotel.domain.models.user.User
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.time.LocalDate


data class UserEntity(
    val userId: Int,
    val fullName: String,
    val password: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val typeUser: UserType,
    val photoLink: String?
) {

    fun toUser(): User {
        return User(userId, fullName, password, email, birthDate, phoneNumber, typeUser, photoLink)
    }

    companion object {
        fun toUserEntity(user: User) = UserEntity (
            user.userId,
            user.fullName,
            user.password,
            user.email,
            user.birthDate,
            user.phoneNumber,
            user.typeUser,
            user.photoLink
        )
    }
}
