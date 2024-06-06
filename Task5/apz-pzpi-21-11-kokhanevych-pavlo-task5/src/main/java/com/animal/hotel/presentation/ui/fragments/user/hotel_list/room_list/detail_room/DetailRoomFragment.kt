package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.detail_room


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.animal.hotel.databinding.FragmentDetailsRoomBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailRoomFragment: BaseFragment<FragmentDetailsRoomBinding>(FragmentDetailsRoomBinding::inflate) {

    private val args: DetailRoomFragmentArgs by navArgs()

    private val viewModel: DetailRoomViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        viewModel.amountOfBlock.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                binding.textAmountOfBlock.text = data.toString()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAmountOfBlocks(args.room.roomId)
        fillInfoColumns()
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonBack.setOnClickListener{findNavController().navigateUp()}
        binding.buttonRentDetails.setOnClickListener{
            val direction =
                DetailRoomFragmentDirections.actionDetailRoomFragmentToFormRentingFragment(
                    args.room, args.data, binding.textAmountOfBlock.text.toString().toInt())
            findNavController().navigate(direction)
        }
    }

    private fun fillInfoColumns() {
        with(binding) {
            textNumberRoomDetail.text = args.room.number.toString()
            textAreaDetail.text = args.room.area.toString()
            textAmountOfBlock.text = viewModel.amountOfBlock.toString()
            textRentingPet.text = args.room.priceHour.toString()
        }
    }
}