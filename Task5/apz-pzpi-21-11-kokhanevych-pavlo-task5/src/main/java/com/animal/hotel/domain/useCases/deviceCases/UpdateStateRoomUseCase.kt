package com.animal.hotel.domain.useCases.deviceCases

import com.animal.hotel.domain.models.device.StateRoomRequest
import com.animal.hotel.domain.sources.DeviceSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateStateRoomUseCase @Inject constructor(
    private val deviceSource: DeviceSource
) {

    suspend operator fun invoke(roomId: Int, stateRoomRequest: StateRoomRequest) {
        withContext(Dispatchers.IO) {
            deviceSource.updateStateOfRoom(roomId, stateRoomRequest)
        }
    }
}