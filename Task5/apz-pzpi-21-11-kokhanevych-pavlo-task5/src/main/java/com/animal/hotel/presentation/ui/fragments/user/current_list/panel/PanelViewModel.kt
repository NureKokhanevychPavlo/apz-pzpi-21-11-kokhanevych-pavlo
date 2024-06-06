package com.animal.hotel.presentation.ui.fragments.user.current_list.panel

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.device.StateRoomRequest
import com.animal.hotel.domain.models.device.StateRoomResponse
import com.animal.hotel.domain.useCases.deviceCases.GetStateRoomUseCase
import com.animal.hotel.domain.useCases.deviceCases.UpdateStateRoomUseCase
import com.animal.hotel.domain.useCases.userCases.UpdateTimeScheduleUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PanelViewModel @Inject constructor(
    private val getStateRoomUseCase: GetStateRoomUseCase,
    private val updateStateRoomUseCase: UpdateStateRoomUseCase,
): BaseViewModel() {

    private val _stateRoom = MutableStateFlow<StateRoomResponse>(StateRoomResponse())
    val stateRoom: StateFlow<StateRoomResponse> = _stateRoom


    fun getStateRoom(roomId: Int) = viewModelScope.safeLaunch {
        _stateRoom.value = getStateRoomUseCase(roomId)
    }

    fun updateStateRoom(roomId: Int,temperature: Float,  humidity: Float, brightness: Int) = viewModelScope.safeLaunch {
        val stateRoomRequest = StateRoomRequest(temperature, humidity, brightness)
        updateStateRoomUseCase(roomId, stateRoomRequest)
    }
}