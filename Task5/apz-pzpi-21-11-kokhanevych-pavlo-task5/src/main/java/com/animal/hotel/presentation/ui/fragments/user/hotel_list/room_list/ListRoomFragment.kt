package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentListRoomsBinding
import com.animal.hotel.domain.models.user.DateRequest
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.history_list.details.DetailRentingFragmentArgs
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.adapter.RoomAdapter
import com.animal.hotel.presentation.ui.interfaces.RoomAdapterListener
import com.animal.hotel.presentation.ui.models.DateRequestUI
import com.animal.hotel.presentation.ui.models.RoomUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ListRoomFragment: BaseFragment<FragmentListRoomsBinding>(FragmentListRoomsBinding::inflate) {

    private val viewModel: ListRoomViewModel by viewModels()

    private val args: ListRoomFragmentArgs by navArgs()

    private lateinit var dateRequest: DateRequestUI

    private val roomAdapter by lazy {
        RoomAdapter(
            listener = object : RoomAdapterListener {
                override fun onItemClick(room: Room) {
                    val direction =
                       ListRoomFragmentDirections.actionListRoomFragmentToDetailRoomFragment(RoomUI.toRoomUI(room), dateRequest)
                    findNavController().navigate(direction)
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.rooms.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                roomAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fillInfoColumns()
        setupFragmentResultListener()
    }

    private fun setupRecyclerView() {
        binding.recyclerRooms.adapter = roomAdapter
        binding.recyclerRooms.layoutManager = LinearLayoutManager(requireContext())
    }

    @SuppressLint("SetTextI18n")
    private fun fillInfoColumns() {
        with(binding) {
            textHotelName.text = args.hotel.name
            textAddressRoom.text = "${args.hotel.city}, ${args.hotel.street}, ${args.hotel.numberHouse}"
        }
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonBack.setOnClickListener{findNavController().navigateUp()}

        binding.buttonFilter.setOnClickListener{
            val direction =
                ListRoomFragmentDirections.actionListRoomFragmentToFilterRoomDialog()
            findNavController().navigate(direction)
        }
    }

    private fun setupFragmentResultListener() {
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            val beginDateStr = bundle.getString(BEGIN_DATE)
            val endDateStr = bundle.getString(END_DATE)
            if (beginDateStr != null && endDateStr != null) {
                val beginDate = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                val endDate = LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                dateRequest = DateRequestUI(beginDate, endDate)
                viewModel.getAllFreeRoom(args.hotel.hotelId, DateRequest(beginDate, endDate))
            }
        }
    }

    companion object {
        const val REQUEST_KEY = "filter_room_request"
        const val BEGIN_DATE = "begin_date"
        const val END_DATE = "end_date"
    }
}