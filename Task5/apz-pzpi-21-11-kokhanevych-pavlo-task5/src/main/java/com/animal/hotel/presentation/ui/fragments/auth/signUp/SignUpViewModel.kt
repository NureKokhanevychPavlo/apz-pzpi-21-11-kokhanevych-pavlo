package com.animal.hotel.presentation.ui.fragments.auth.signUp

import androidx.lifecycle.viewModelScope
import com.animal.hotel.data.utils.ValidationUser
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.useCases.authCases.RegisterUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
): BaseViewModel() {

    fun registerUser(user: User) = viewModelScope.safeLaunch {
        registerUserUseCase(user)
    }

    fun isEmailCorrect(email: String?): Boolean = ValidationUser.isEmailCorrect(email)

    fun isPasswordCorrect(password: String?): Boolean = ValidationUser.isPasswordCorrect(password)

    fun isPasswordsSame(password: String?, againPassword: String?): Boolean = ValidationUser.isPasswordsSame(password, againPassword)

    fun isPhoneNumberCorrect(phoneNumber: String?): Boolean = ValidationUser.isPhoneNumberCorrect(phoneNumber)

    fun isOldEnough(birthDate: LocalDate?): Boolean = ValidationUser.isOldEnough(birthDate)
}