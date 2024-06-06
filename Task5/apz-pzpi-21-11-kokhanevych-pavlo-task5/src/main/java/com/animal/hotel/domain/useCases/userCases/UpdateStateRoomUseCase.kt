package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.data.device.source.RetrofitDeviceSource
import com.animal.hotel.domain.models.device.StateRoomRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateStateRoomUseCase @Inject constructor(
    private val deviceSource: RetrofitDeviceSource
) {

    suspend operator fun invoke(roomId: Int, stateRoomRequest: StateRoomRequest) {
        withContext(Dispatchers.IO) {
            deviceSource.updateStateOfRoom(roomId, stateRoomRequest)
        }
    }
}