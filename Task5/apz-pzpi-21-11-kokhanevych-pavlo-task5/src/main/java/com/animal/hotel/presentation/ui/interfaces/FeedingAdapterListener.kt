package com.animal.hotel.presentation.ui.interfaces

import com.animal.hotel.domain.models.user.ScheduleFeedResponse

interface FeedingAdapterListener {
    fun onItemClick(scheduleFeedResponse: ScheduleFeedResponse)
}