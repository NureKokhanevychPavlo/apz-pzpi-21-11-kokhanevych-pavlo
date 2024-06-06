package com.animal.hotel.presentation.ui.fragments.auth.signIn


import androidx.lifecycle.viewModelScope
import com.animal.hotel.data.utils.ValidationUser
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.authentication.SignInRequest
import com.animal.hotel.domain.useCases.authCases.SignInUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    suspend fun signIn(signInRequest: SignInRequest) = viewModelScope.safeLaunch() {
        appSettings.setCurrentToken("")
        appSettings.setUserId(null)
        val authenticationResponse = signInUseCase(signInRequest)
        if (authenticationResponse.token != "") {
            isTokenPresent.value = true
        }
    }

    fun isEmailCorrect(email: String?): Boolean = ValidationUser.isEmailCorrect(email)

    fun isPasswordCorrect(password: String?): Boolean = ValidationUser.isPasswordCorrect(password)
}