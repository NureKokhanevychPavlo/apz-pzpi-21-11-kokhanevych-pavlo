package com.animal.hotel.presentation.ui.fragments.user.current_list.feeding_list

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.useCases.userCases.GetScheduleFeedUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListFeedingViewModel @Inject constructor(
    private val getScheduleFeedUseCase: GetScheduleFeedUseCase
): BaseViewModel() {

    private val _scheduleFeeding = MutableStateFlow<List<ScheduleFeedResponse>>(emptyList())
    val scheduleFeeding: StateFlow<List<ScheduleFeedResponse>> = _scheduleFeeding

    fun getScheduleFeeding(rentId: Int) = viewModelScope.safeLaunch {
        _scheduleFeeding.value = getScheduleFeedUseCase(rentId)
    }
}