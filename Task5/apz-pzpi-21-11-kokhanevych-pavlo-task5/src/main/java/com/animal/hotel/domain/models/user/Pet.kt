package com.animal.hotel.domain.models.user

import com.animal.hotel.utils.enums.PetType

data class Pet(
    val petId: Int = 0,
    val name: String,
    val age: Int,
    val weight: Float,
    val typePet: PetType,
    val description: String?,
    val photoLink: String?,
    val user: User
)
