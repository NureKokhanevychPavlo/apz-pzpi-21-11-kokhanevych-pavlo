package com.animal.hotel.data.user.entities

import com.animal.hotel.utils.enums.PetType
import com.animal.hotel.domain.models.user.Pet
import com.google.gson.annotations.SerializedName


data class PetEntity(
    val petId: Int,
    val name: String,
    val age: Int,
    val weight: Float,
    val typePet: PetType,
    val description: String?,
    val photoLink: String?,
    val user: UserEntity
) {
    fun toPet(): Pet {
        return Pet(petId, name, age, weight, typePet, description, photoLink, user.toUser())
    }

    companion object {
        fun toPetEntity(pet: Pet) = PetEntity (
            pet.petId,
            pet.name,
            pet.age,
            pet.weight,
            pet.typePet,
            pet.description,
            pet.photoLink,
            UserEntity.toUserEntity(pet.user)
        )
    }
}