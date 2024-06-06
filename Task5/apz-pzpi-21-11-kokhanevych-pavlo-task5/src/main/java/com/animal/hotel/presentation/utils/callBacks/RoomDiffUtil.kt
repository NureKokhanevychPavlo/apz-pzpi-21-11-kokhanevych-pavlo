package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.Room

class RoomDiffUtil: DiffUtil.ItemCallback<Room>() {

    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean = oldItem.roomId == newItem.roomId

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean = oldItem == newItem
}