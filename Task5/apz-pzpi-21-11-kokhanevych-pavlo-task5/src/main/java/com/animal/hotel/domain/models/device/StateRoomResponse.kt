package com.animal.hotel.domain.models.device

import com.animal.hotel.domain.models.user.BlockOfFood

data class StateRoomResponse(
    val temperature: Float = 0f,
    val humidity: Float = 0f,
    val brightness: Int = 0,
    val blocksOfFood: List<BlockOfFood> = emptyList()
)
