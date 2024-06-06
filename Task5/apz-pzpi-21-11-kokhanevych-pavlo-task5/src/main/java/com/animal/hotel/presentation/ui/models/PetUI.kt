package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.utils.enums.PetType
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class PetUI(
    val petId: Int,
    val name: String,
    val age: Int,
    val weight: Float,
    val typePet: PetType,
    val description: String?,
    val photoLink: String?,
     val user: UserUI
): Parcelable {
    fun toPet(): Pet {
        return Pet(petId, name, age, weight, typePet, description, photoLink, user.toUser())
    }

    companion object {
        fun toPetUI(pet: Pet) = PetUI (
            pet.petId,
            pet.name,
            pet.age,
            pet.weight,
            pet.typePet,
            pet.description,
            pet.photoLink,
            UserUI.toUserUI(pet.user)
        )
    }
}