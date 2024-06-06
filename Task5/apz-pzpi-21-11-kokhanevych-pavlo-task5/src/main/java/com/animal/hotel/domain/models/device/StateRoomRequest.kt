package com.animal.hotel.domain.models.device

data class StateRoomRequest(
    val temperature: Float,
    val humidity: Float,
    val brightness: Int
)
