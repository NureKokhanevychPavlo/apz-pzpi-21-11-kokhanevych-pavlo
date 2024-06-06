package com.animal.hotel.presentation.ui.fragments.user.current_list

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.domain.useCases.userCases.GetCurrentRentingUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class CurrentRentingViewModel @Inject constructor(
    private val getCurrentRentingUseCase: GetCurrentRentingUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    private val _currentRenting = MutableStateFlow<List<HistoryRentingResponse>>(emptyList())
    val currentRenting: StateFlow<List<HistoryRentingResponse>> = _currentRenting

    fun getHistoryRenting() {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.let {
                _currentRenting.value = getCurrentRentingUseCase(it).first()
            }
        }
    }
}