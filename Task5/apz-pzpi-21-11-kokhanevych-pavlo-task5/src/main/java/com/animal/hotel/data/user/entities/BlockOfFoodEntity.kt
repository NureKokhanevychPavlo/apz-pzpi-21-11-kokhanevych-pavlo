package com.animal.hotel.data.user.entities

import com.animal.hotel.utils.enums.FoodType
import com.animal.hotel.domain.models.user.BlockOfFood
import com.google.gson.annotations.SerializedName

data class BlockOfFoodEntity(
    val foodType: FoodType,
    val portion: Float,
    val levelOfFilling: Int
) {
    fun toBlockOfFood(): BlockOfFood {
        return BlockOfFood(foodType, portion, levelOfFilling)
    }

    companion object {
        fun toBlockOfFoodEntity(food: BlockOfFood) = BlockOfFoodEntity (
            food.foodType,
            food.portion,
            food.levelOfFilling
        )
    }
}
