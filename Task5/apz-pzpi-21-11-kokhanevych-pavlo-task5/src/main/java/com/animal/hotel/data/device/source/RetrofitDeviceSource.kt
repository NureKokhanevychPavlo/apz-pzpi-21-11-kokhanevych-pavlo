package com.animal.hotel.data.device.source

import com.animal.hotel.data.ExceptionHandler
import com.animal.hotel.data.device.api.DeviceApi
import com.animal.hotel.data.device.entities.StateRoomRequestEntity
import com.animal.hotel.data.device.entities.StateRoomResponseEntity
import com.animal.hotel.domain.models.device.StateRoomRequest
import com.animal.hotel.domain.models.device.StateRoomResponse
import com.animal.hotel.domain.sources.DeviceSource
import okhttp3.ResponseBody
import javax.inject.Inject

class RetrofitDeviceSource @Inject constructor(
    private val deviceApi: DeviceApi
): ExceptionHandler(), DeviceSource {

    override suspend fun getStateOfRoom(roomId: Int): StateRoomResponse = wrapRetrofitExceptions {
        deviceApi.getStateOfRoom(roomId).toStateRoomResponse()
    }

    override suspend fun setNewStateOfRoom(roomId: Int, roomResponse: StateRoomResponse) = wrapRetrofitExceptions {
        deviceApi.setNewStateOfRoom(roomId, StateRoomResponseEntity.toStateRoomResponseEntity(roomResponse))
    }

   override suspend fun updateStateOfRoom(roomId: Int, stateRoomRequest: StateRoomRequest) = wrapRetrofitExceptions {
        deviceApi.updateStateOfRoom(roomId, StateRoomRequestEntity.toStateRoomRequestEntity(stateRoomRequest))
    }

   override suspend fun startVideoStream(roomId: Int): ResponseBody = wrapRetrofitExceptions {
        deviceApi.startVideoStream(roomId)
    }
}