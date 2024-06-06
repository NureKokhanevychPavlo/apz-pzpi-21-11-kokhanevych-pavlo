package com.animal.hotel.domain.useCases.deviceCases

import com.animal.hotel.domain.models.device.StateRoomResponse
import com.animal.hotel.domain.sources.DeviceSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStateRoomUseCase @Inject constructor(
    private val deviceSource: DeviceSource
) {
    suspend operator fun invoke(roomId: Int): StateRoomResponse {
        return withContext(Dispatchers.IO) {
            deviceSource.getStateOfRoom(roomId)
        }
    }
}