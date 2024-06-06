package com.animal.hotel.domain.sources


import com.animal.hotel.domain.models.device.StateRoomRequest
import com.animal.hotel.domain.models.device.StateRoomResponse
import okhttp3.ResponseBody

interface DeviceSource {

    suspend fun getStateOfRoom(roomId: Int): StateRoomResponse

    suspend fun setNewStateOfRoom(roomId: Int, roomResponse: StateRoomResponse)

    suspend fun updateStateOfRoom(roomId: Int, stateRoomRequest: StateRoomRequest)

    suspend fun startVideoStream(roomId: Int): ResponseBody
}