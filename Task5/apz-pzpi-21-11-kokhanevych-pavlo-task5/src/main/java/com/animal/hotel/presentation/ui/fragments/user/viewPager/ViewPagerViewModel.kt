package com.animal.hotel.presentation.ui.fragments.user.viewPager

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.useCases.userCases.GetPhotoUseCase
import com.animal.hotel.domain.useCases.userCases.GetUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class ViewPagerViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    private val _userName = MutableStateFlow<String>("")
    val userName: StateFlow<String> = _userName

    private var _userPhoto = MutableStateFlow<ByteArray>(ByteArray(0))
    var userPhoto: StateFlow<ByteArray> = _userPhoto

    fun getUserName() {
        viewModelScope.safeLaunch {
            val email = appSettings.getEmail()
            email?.run {
                _userName.value = getUserUseCase(email).fullName
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
}