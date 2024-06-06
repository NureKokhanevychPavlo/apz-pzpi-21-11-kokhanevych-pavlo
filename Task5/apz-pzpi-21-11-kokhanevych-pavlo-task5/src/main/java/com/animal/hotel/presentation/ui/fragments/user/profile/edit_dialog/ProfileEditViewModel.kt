package com.animal.hotel.presentation.ui.fragments.user.profile.edit_dialog

import androidx.lifecycle.viewModelScope
import com.animal.hotel.data.utils.ValidationUser
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.models.user.UserRequest
import com.animal.hotel.domain.useCases.userCases.GetPhotoUseCase
import com.animal.hotel.domain.useCases.userCases.GetUserUseCase
import com.animal.hotel.domain.useCases.userCases.SaveUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getPhotoUseCase: GetPhotoUseCase,
    private val appSettings: AppSettings
): BaseViewModel()  {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private var _userPhoto = MutableStateFlow<ByteArray>(ByteArray(0))
    var userPhoto: StateFlow<ByteArray> = _userPhoto

     var userLink: String? = null

    fun getUser() {
        viewModelScope.safeLaunch {
            val email = appSettings.getEmail()
            email?.run {
                _user.value = getUserUseCase(email)
                userLink = _user.value?.photoLink
            }
        }
    }

    fun getUserPhoto() {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.run {
                _userPhoto.value = getPhotoUseCase(userId)?: ByteArray(0)
            }
        }
    }

    fun saveUser(user: User) = viewModelScope.safeLaunch {
        if (user.photoLink.equals("null")) {
            user.photoLink = userLink
            saveUserUseCase(user, null)
        } else {
            saveUserUseCase(user, user.photoLink)
        }
    }
//    user.photoLink.equals(userLink)

    fun isEmailCorrect(email: String?): Boolean = ValidationUser.isEmailCorrect(email)

    fun isPhoneNumberCorrect(phoneNumber: String?): Boolean = ValidationUser.isPhoneNumberCorrect(phoneNumber)

    fun isOldEnough(birthDate: LocalDate?): Boolean = ValidationUser.isOldEnough(birthDate)
}