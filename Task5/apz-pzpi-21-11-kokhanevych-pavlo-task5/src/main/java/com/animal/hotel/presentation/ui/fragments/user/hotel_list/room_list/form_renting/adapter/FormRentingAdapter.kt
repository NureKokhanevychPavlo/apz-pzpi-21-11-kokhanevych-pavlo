package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemFeedingBinding
import com.animal.hotel.databinding.ItemRoomBinding
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.presentation.ui.interfaces.FormRentingAdapterListener
import com.animal.hotel.presentation.utils.callBacks.FormRentingDiffUtil

class FormRentingAdapter (
    val listener: FormRentingAdapterListener
): ListAdapter<Schedule, FormRentingAdapter.FormRentingViewHolder>(
    FormRentingDiffUtil())  {

    inner class FormRentingViewHolder(
        private val binding: ItemFeedingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(schedule: Schedule) {
            with(binding) {
                textDateTime.text = schedule.dateTime.toString()
                textPortion.text = schedule.diet.portion.toString()
                textFoodType.text = schedule.diet.typeFood.name
                buttonBasket.setOnClickListener {
                    listener.onDeleteClick(schedule)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FormRentingViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemFeedingBinding.inflate(
            inflate,
            parent,
            false
        )
        return FormRentingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FormRentingViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}