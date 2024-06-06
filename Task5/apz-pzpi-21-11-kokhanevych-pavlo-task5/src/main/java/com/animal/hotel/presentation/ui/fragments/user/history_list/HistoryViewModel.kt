package com.animal.hotel.presentation.ui.fragments.user.history_list

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.domain.useCases.userCases.GetHistoryRentingUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryRentingUseCase: GetHistoryRentingUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    private val _history = MutableStateFlow<List<HistoryRentingResponse>>(emptyList())
    val history: StateFlow<List<HistoryRentingResponse>> = _history

    fun getHistory() {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.run {
                _history.value = getHistoryRentingUseCase(userId).first()
            }
        }
    }
}