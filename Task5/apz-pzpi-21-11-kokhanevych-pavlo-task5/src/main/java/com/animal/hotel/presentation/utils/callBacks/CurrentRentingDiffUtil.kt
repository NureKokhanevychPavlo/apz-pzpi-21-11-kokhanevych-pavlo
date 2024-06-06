package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.HistoryRentingResponse

class CurrentRentingDiffUtil: DiffUtil.ItemCallback<HistoryRentingResponse>() {

    override fun areItemsTheSame(oldItem: HistoryRentingResponse, newItem: HistoryRentingResponse): Boolean = oldItem.roomId == newItem.roomId

    override fun areContentsTheSame(oldItem: HistoryRentingResponse, newItem: HistoryRentingResponse): Boolean = oldItem == newItem
}