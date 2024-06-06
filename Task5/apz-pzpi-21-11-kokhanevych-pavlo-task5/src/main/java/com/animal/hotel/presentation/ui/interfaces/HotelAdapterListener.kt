package com.animal.hotel.presentation.ui.interfaces


import com.animal.hotel.domain.models.user.Hotel

interface HotelAdapterListener {
    fun onItemClick(hotel: Hotel)
}