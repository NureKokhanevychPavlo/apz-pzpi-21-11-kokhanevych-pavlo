package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.utils.enums.UserType
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class UserUI(
    val userId: Int,
    val fullName: String,
    val password: String,
    val email: String,
    val birthDate: LocalDate,
    val phoneNumber: String,
    val typeUser: UserType,
    val photoLink: String?
): Parcelable {
    fun toUser(): User {
        return User(userId, fullName, password, email, birthDate, phoneNumber, typeUser, photoLink)
    }

    companion object {
        fun toUserUI(user: User) = UserUI (
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