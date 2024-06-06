package com.animal.hotel.presentation.ui.fragments.user.pets_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemAnimalBinding
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.presentation.ui.interfaces.PetAdapterListener
import com.animal.hotel.presentation.utils.callBacks.PetsDiffUtil

class PetsAdapter (
    val listener: PetAdapterListener
): ListAdapter<Pet, PetsAdapter.PetViewHolder>(
    PetsDiffUtil()
) {
    inner class PetViewHolder(
        private val binding: ItemAnimalBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(pet: Pet) {
            with(binding) {
                textName.text = pet.name
                textTypePet.text = pet.typePet.name
                root.setOnClickListener {
                    listener.onItemClick(pet)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemAnimalBinding.inflate(
            inflate,
            parent,
            false
        )
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PetViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}