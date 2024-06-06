package com.animal.hotel.presentation.ui.interfaces

import com.animal.hotel.domain.models.user.Room

interface RoomAdapterListener {
    fun onItemClick(room: Room)
}