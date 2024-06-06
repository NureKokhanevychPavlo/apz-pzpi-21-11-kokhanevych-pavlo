package com.animal.hotel.data.device.entities

import com.animal.hotel.domain.models.device.StateRoomRequest

data class StateRoomRequestEntity(
    val temperature: Float,
    val humidity: Float,
    val brightness: Int
){
    fun toStateRoomRequest(): StateRoomRequest {
        return StateRoomRequest(temperature, humidity, brightness)
    }

    companion object {
        fun toStateRoomRequestEntity(request: StateRoomRequest) = StateRoomRequestEntity (
            request.temperature,
            request.humidity,
            request.brightness
        )
    }
}
