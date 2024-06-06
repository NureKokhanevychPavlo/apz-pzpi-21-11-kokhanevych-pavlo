package com.animal.hotel.presentation.ui.fragments.user.current_list.feeding_list.edit_feeding

import androidx.lifecycle.viewModelScope
import com.animal.hotel.data.utils.ValidationFood
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.useCases.userCases.UpdateTimeScheduleUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class EditFeedingFragmentViewModel @Inject constructor(
    private val updateTimeScheduleUseCase: UpdateTimeScheduleUseCase
): BaseViewModel() {

    fun updateScheduleUseCase(scheduleFeedResponse: ScheduleFeedResponse) = viewModelScope.safeLaunch {
        updateTimeScheduleUseCase(listOf(scheduleFeedResponse))
    }

    fun isDataTimeCorrect(data: LocalDateTime): Boolean {
        return ValidationFood.isDateOfFeedingCorrect(data)
    }
}