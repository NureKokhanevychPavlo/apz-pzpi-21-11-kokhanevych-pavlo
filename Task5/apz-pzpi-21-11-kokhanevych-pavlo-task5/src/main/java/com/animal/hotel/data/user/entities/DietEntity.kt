package com.animal.hotel.data.user.entities

import com.animal.hotel.utils.enums.FoodType
import com.animal.hotel.domain.models.user.Diet

data class DietEntity(
    val dietId: Int,
    val portion: Float,
    val typeFood: FoodType,
    val rent: RentEntity
) {
    fun toDiet(): Diet {
        return  Diet(dietId, portion, typeFood, rent.toRent())
    }

    companion object {
        fun toDietEntity(diet: Diet) =  DietEntity (
            diet.dietId,
            diet.portion,
            diet.typeFood,
            RentEntity.toRentEntity(diet.rent)
        )
    }
}
