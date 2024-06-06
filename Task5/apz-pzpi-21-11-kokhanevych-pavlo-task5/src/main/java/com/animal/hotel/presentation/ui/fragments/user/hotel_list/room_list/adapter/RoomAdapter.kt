package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemRoomBinding
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.presentation.ui.interfaces.RoomAdapterListener
import com.animal.hotel.presentation.utils.callBacks.RoomDiffUtil

class RoomAdapter(
    val listener: RoomAdapterListener
): ListAdapter<Room, RoomAdapter.RoomViewHolder>(
    RoomDiffUtil()
)  {

    inner class RoomViewHolder(
        private val binding: ItemRoomBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(room: Room) {
            with(binding) {
                textNumberItem.text = room.number.toString()
                textPriceHour.text = room.priceHour.toString()
                root.setOnClickListener {
                    listener.onItemClick(room)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RoomViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemRoomBinding.inflate(
            inflate,
            parent,
            false
        )
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RoomViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}