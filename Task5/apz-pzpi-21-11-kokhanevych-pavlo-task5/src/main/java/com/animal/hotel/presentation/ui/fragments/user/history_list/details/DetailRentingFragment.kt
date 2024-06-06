package com.animal.hotel.presentation.ui.fragments.user.history_list.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.animal.hotel.databinding.FragmentDetailRentingBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailRentingFragment: BaseFragment<FragmentDetailRentingBinding>(FragmentDetailRentingBinding::inflate) {

    private val args: DetailRentingFragmentArgs by navArgs()

    override fun setListeners() {
        super.setListeners()
        binding.buttonBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillHistory()
    }

    private fun fillHistory() {
        with(binding) {
            textBeginRentingDate.text = args.history.beginDate.toString()
            textEndDate.text = args.history.endDate.toString()
            textRentingPet.text= args.history.petName
            textNumberRoom.text = args.history.numberRoom.toString()
            textNameHotelRenting.text = args.history.hotelName
            textTotalPayment.text = args.history.totalPayment.toString()
        }
    }
}