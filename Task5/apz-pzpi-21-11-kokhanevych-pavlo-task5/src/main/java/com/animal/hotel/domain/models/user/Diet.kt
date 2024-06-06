package com.animal.hotel.domain.models.user

import com.animal.hotel.utils.enums.FoodType

data class Diet(
    val dietId: Int = 0,
    val portion: Float,
    val typeFood: FoodType,
    val rent: Rent
)
