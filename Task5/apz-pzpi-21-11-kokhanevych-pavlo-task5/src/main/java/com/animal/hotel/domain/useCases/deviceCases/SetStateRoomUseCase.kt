package com.animal.hotel.domain.useCases.deviceCases

import com.animal.hotel.domain.models.device.StateRoomResponse
import com.animal.hotel.domain.sources.DeviceSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetStateRoomUseCase @Inject constructor(
    private val deviceSource: DeviceSource
) {
    suspend operator fun invoke(roomId: Int, roomResponse: StateRoomResponse) {
        withContext(Dispatchers.IO) {
            deviceSource.setNewStateOfRoom(roomId, roomResponse)
        }
    }
}