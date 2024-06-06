package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list

import androidx.lifecycle.viewModelScope
import com.animal.hotel.data.utils.ValidationRenting
import com.animal.hotel.domain.models.user.DateRequest
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.domain.useCases.userCases.GetFreeRoomsUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ListRoomViewModel  @Inject constructor(
    private val getFreeRoomsUseCase: GetFreeRoomsUseCase,
): BaseViewModel() {

    private val _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms: StateFlow<List<Room>> = _rooms

    fun getAllFreeRoom(hotelId: Int, dateRequest: DateRequest) {
        viewModelScope.safeLaunch {
            _rooms.value = getFreeRoomsUseCase(hotelId, dateRequest).first()
        }
    }

    fun isRentingDatesCorrect(beginDate: LocalDateTime?, endDate: LocalDateTime?): Boolean {
        return ValidationRenting.isRentingDatesCorrect(beginDate, endDate)
    }
}