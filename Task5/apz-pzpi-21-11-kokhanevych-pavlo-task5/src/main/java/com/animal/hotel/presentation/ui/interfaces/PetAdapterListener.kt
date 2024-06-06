package com.animal.hotel.presentation.ui.interfaces

import com.animal.hotel.domain.models.user.Pet

interface PetAdapterListener {
    fun onItemClick(pet: Pet)
}