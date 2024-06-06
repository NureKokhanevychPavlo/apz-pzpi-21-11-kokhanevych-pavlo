package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.Schedule

class FormRentingDiffUtil: DiffUtil.ItemCallback<Schedule>() {

    override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean = oldItem.scheduleId == newItem.scheduleId

    override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean = oldItem == newItem
}