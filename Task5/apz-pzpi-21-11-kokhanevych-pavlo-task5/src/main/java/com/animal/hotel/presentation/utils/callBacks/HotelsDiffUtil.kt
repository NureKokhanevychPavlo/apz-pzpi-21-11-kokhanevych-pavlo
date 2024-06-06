package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.Hotel

class HotelsDiffUtil: DiffUtil.ItemCallback<Hotel>() {

    override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean = oldItem.hotelId == newItem.hotelId

    override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean = oldItem == newItem
}