package com.animal.hotel.presentation.ui.interfaces

import com.animal.hotel.domain.models.user.Schedule

interface FormRentingAdapterListener {
    fun onDeleteClick(schedule: Schedule)
}