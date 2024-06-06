package com.animal.hotel.data.device.entities

import com.animal.hotel.data.user.entities.BlockOfFoodEntity
import com.animal.hotel.domain.models.device.StateRoomResponse
import com.google.gson.annotations.SerializedName

data class StateRoomResponseEntity(
    val temperature: Float,
    val humidity: Float,
    val brightness: Int,
    val blocksOfFood: List<BlockOfFoodEntity>
) {
    fun toStateRoomResponse(): StateRoomResponse {
        return StateRoomResponse(temperature, humidity, brightness, blocksOfFood.map { it.toBlockOfFood() })
    }

    companion object {
        fun toStateRoomResponseEntity(response: StateRoomResponse) = StateRoomResponseEntity (
            response.temperature,
            response.humidity,
            response.brightness,
            response.blocksOfFood.map { BlockOfFoodEntity.toBlockOfFoodEntity(it) }
        )
    }
}
