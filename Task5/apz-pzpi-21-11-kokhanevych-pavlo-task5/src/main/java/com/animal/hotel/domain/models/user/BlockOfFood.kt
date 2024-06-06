package com.animal.hotel.domain.models.user

import com.animal.hotel.utils.enums.FoodType

data class BlockOfFood(
    val foodType: FoodType,
    val portion: Float,
    val levelOfFilling: Int
)
