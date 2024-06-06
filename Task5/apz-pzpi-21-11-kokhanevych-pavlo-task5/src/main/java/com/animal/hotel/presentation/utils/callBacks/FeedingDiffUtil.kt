package com.animal.hotel.presentation.utils.callBacks

import androidx.recyclerview.widget.DiffUtil
import com.animal.hotel.domain.models.user.ScheduleFeedResponse

class FeedingDiffUtil: DiffUtil.ItemCallback<ScheduleFeedResponse>() {

    override fun areItemsTheSame(oldItem: ScheduleFeedResponse, newItem: ScheduleFeedResponse): Boolean = oldItem.scheduleId == newItem.scheduleId

    override fun areContentsTheSame(oldItem: ScheduleFeedResponse, newItem: ScheduleFeedResponse): Boolean = oldItem == newItem
}