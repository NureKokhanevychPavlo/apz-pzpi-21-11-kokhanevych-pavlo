package com.animal.hotel.presentation.ui.interfaces

import com.animal.hotel.domain.models.user.HistoryRentingResponse

interface HistoryAdapterListener {
    fun onItemClick(historyRentingResponse: HistoryRentingResponse)
}