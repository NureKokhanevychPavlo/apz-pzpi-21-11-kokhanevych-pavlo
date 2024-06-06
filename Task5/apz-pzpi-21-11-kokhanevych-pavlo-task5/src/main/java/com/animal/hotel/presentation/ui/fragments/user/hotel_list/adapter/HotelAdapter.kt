package com.animal.hotel.presentation.ui.fragments.user.hotel_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemHotelBinding
import com.animal.hotel.domain.models.user.Hotel
import com.animal.hotel.presentation.ui.interfaces.HotelAdapterListener
import com.animal.hotel.presentation.utils.callBacks.HotelsDiffUtil

class HotelAdapter(
    val listener: HotelAdapterListener
): ListAdapter<Hotel, HotelAdapter.HotelViewHolder>(
    HotelsDiffUtil()
) {

    inner class HotelViewHolder(
        private val binding: ItemHotelBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(hotel: Hotel) {
            with(binding) {
                textNameHotelItem.text = hotel.name
                textAddressHotelItem.text = "${hotel.city}, ${hotel.street}, ${hotel.numberHouse}"
                root.setOnClickListener {
                    listener.onItemClick(hotel)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemHotelBinding.inflate(
            inflate,
            parent,
            false
        )
        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HotelViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}