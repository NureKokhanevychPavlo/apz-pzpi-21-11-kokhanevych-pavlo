package com.animal.hotel.presentation.ui.fragments.user.current_list.panel.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemBlockFoodBinding
import com.animal.hotel.domain.models.user.BlockOfFood
import com.animal.hotel.presentation.utils.callBacks.BlocksFoodDiffUtil

class PanelAdapter(): ListAdapter<BlockOfFood, PanelAdapter.PanelViewHolder>(BlocksFoodDiffUtil()){

    inner class PanelViewHolder(
        private val binding: ItemBlockFoodBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(blockOfFood: BlockOfFood) {
            with(binding) {
                textPortion.text = blockOfFood.portion.toString()
                textFoodType.text = blockOfFood.foodType.name
                textFilling.text = "${blockOfFood.levelOfFilling} %"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PanelViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemBlockFoodBinding.inflate(
            inflate,
            parent,
            false
        )
        return PanelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PanelViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}