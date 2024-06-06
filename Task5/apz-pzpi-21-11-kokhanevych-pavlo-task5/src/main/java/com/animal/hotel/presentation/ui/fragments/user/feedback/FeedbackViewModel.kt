package com.animal.hotel.presentation.ui.fragments.user.feedback

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.useCases.userCases.SendMessageUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FeedbackViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val appSettings: AppSettings
): BaseViewModel()  {

    fun sendMessage(text: String) {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.run {
                 sendMessageUseCase(userId, text)
            }
        }
    }
}