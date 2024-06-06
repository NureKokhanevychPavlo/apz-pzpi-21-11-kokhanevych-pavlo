package com.animal.hotel.presentation.ui.fragments.user.current_list.feeding_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemFeedingBinding
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.presentation.ui.interfaces.FeedingAdapterListener
import com.animal.hotel.presentation.utils.callBacks.FeedingDiffUtil

class FeedingAdapter (
    val listener: FeedingAdapterListener
): ListAdapter<ScheduleFeedResponse, FeedingAdapter.FeedingViewHolder>(FeedingDiffUtil()){

    inner class FeedingViewHolder(
        private val binding: ItemFeedingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(scheduleFeedResponse: ScheduleFeedResponse) {
            with(binding) {
                textDateTime.text = scheduleFeedResponse.dateTime.toString()
                textPortion.text = scheduleFeedResponse.portion.toString()
                textFoodType.text = scheduleFeedResponse.typeFood.name
                root.setOnClickListener {
                    listener.onItemClick(scheduleFeedResponse)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedingViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding =  ItemFeedingBinding.inflate(
            inflate,
            parent,
            false
        )
        return FeedingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FeedingViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}