package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.Pet

class PetsDiffUtil: DiffUtil.ItemCallback<Pet>() {

    override fun areItemsTheSame(oldItem: Pet, newItem: Pet): Boolean = oldItem.petId == newItem.petId

    override fun areContentsTheSame(oldItem: Pet, newItem: Pet): Boolean = oldItem == newItem
}