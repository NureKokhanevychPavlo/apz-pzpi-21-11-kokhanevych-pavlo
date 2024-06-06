package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.BlockOfFood

class BlocksFoodDiffUtil: DiffUtil.ItemCallback<BlockOfFood>() {

    override fun areItemsTheSame(oldItem: BlockOfFood, newItem: BlockOfFood): Boolean = oldItem.levelOfFilling == newItem.levelOfFilling

    override fun areContentsTheSame(oldItem: BlockOfFood, newItem: BlockOfFood): Boolean = oldItem == newItem
}