package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.detail_room

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.useCases.deviceCases.GetStateRoomUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailRoomViewModel @Inject constructor(
    private val getStateRoomUseCase: GetStateRoomUseCase
): BaseViewModel() {

    private val _amountOfBlock = MutableStateFlow<Int>(0)
    val amountOfBlock: StateFlow<Int> = _amountOfBlock

    fun getAmountOfBlocks(roomId: Int) {
        viewModelScope.safeLaunch {
            _amountOfBlock.value = getStateRoomUseCase(roomId).blocksOfFood.size
        }
    }
}