package com.animal.hotel.presentation.ui.fragments.user.profile

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.useCases.userCases.DeleteUserUseCase
import com.animal.hotel.domain.useCases.userCases.GetPhotoUseCase
import com.animal.hotel.domain.useCases.userCases.GetUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getPhotoUseCase: GetPhotoUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private var _userPhoto = MutableStateFlow<ByteArray>(ByteArray(0))
    var userPhoto: StateFlow<ByteArray> = _userPhoto

    fun getUser() {
        viewModelScope.safeLaunch {
            val email = appSettings.getEmail()
            email?.run {
                _user.value = getUserUseCase(email)
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

    fun deleteUser() {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.run {
                deleteUserUseCase(userId)
            }
        }
    }
}