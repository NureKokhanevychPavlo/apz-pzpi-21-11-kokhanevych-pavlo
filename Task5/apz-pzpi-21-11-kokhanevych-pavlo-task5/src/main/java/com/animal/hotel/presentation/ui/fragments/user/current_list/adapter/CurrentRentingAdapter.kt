package com.animal.hotel.presentation.ui.fragments.user.current_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.animal.hotel.presentation.ui.interfaces.CurrentRentingAdapterListener
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.animal.hotel.databinding.ItemCurrentRentingBinding
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.presentation.utils.callBacks.CurrentRentingDiffUtil

class CurrentRentingAdapter(
    val listener: CurrentRentingAdapterListener
): ListAdapter<HistoryRentingResponse, CurrentRentingAdapter.CurrentRentingViewHolder>(CurrentRentingDiffUtil()){

    inner class CurrentRentingViewHolder(
        private val binding: ItemCurrentRentingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(historyRentingResponse: HistoryRentingResponse) {
            with(binding) {
                textBeginRentingItem.text = historyRentingResponse.beginDate.toString()
                textEndDate.text = historyRentingResponse.endDate.toString()
                textPetName.text = historyRentingResponse.petName
                textTotal.text = historyRentingResponse.totalPayment.toString()
                root.setOnClickListener {
                    listener.onItemClick(historyRentingResponse)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrentRentingViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemCurrentRentingBinding.inflate(
            inflate,
            parent,
            false
        )
        return CurrentRentingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CurrentRentingViewHolder,
        position: Int
    ) = holder.bind(getItem(position))
}