package com.animal.hotel.presentation.ui.fragments.user.current_list.panel

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.R
import com.animal.hotel.databinding.FragmentFeedbackBinding
import com.animal.hotel.databinding.FragmentPanelBinding
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.current_list.panel.adapter.PanelAdapter
import com.animal.hotel.presentation.ui.fragments.user.feedback.FeedbackViewModel
import com.animal.hotel.presentation.ui.fragments.user.history_list.details.DetailRentingFragmentArgs
import com.animal.hotel.presentation.ui.fragments.user.pets_list.adapter.PetsAdapter
import com.animal.hotel.presentation.ui.fragments.user.viewPager.ViewPagerFragmentDirections
import com.animal.hotel.presentation.ui.interfaces.PetAdapterListener
import com.animal.hotel.presentation.ui.models.HistoryRentingResponseUI
import com.animal.hotel.presentation.ui.models.PetUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PanelFragment: BaseFragment<FragmentPanelBinding>(FragmentPanelBinding::inflate) {

    private val viewModel: PanelViewModel by viewModels()

    private val args: PanelFragmentArgs by navArgs()

    private val panelAdapter by lazy { PanelAdapter() }

    override fun onStart() {
        super.onStart()
        viewModel.stateRoom.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                with(binding) {
                    textValueTemperature.text = data.temperature.toString()
                    textValueHumidity.text = data.humidity.toString()
                    textValueWishTemperature.text = data.temperature.toString()
                    textValueWishHumidity.text = data.humidity.toString()
                    brightnessSeekBar.progress = data.brightness
                    panelAdapter.submitList(data.blocksOfFood)
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonBackPanel.setOnClickListener{findNavController().navigateUp()}
        setButtonSaveStateRoomListener()
        setButtonsControlTemperatureListener()
        setButtonControlHumidityListener()
        binding.buttonFeedingPanel.setOnClickListener{
            val direction =
                PanelFragmentDirections.actionPanelFragmentToListFeedingFragment(
                    binding.textValueWishTemperature.text.toString().toFloat(),
                    binding.textValueWishHumidity.text.toString().toFloat(),
                    binding.brightnessSeekBar.progress,
                    args.renting.rentId
                )
            findNavController().navigate(direction)
        }
    }

    private fun setButtonSaveStateRoomListener() {
        with(binding) {
            buttonApply.setOnClickListener{
                viewModel.updateStateRoom(
                    args.renting.roomId,
                    textValueWishTemperature.text.toString().toFloat(),
                    textValueWishHumidity.text.toString().toFloat(),
                    brightnessSeekBar.progress
                )
                Toast.makeText(requireContext(), getString(R.string.message_successfully), Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun setButtonsControlTemperatureListener() {
        with(binding) {
            buttonDropUpTemp.setOnClickListener {
                val valueWishTemperature = textValueWishTemperature.text.toString().toFloat()
                val newValue = valueWishTemperature + 0.1f
                textValueWishTemperature.text = String.format("%.1f", newValue)
            }

            buttonDropDownTemp.setOnClickListener {
                val valueWishTemperature = textValueWishTemperature.text.toString().toFloat()
                if (valueWishTemperature > 0) {
                    val newValue = valueWishTemperature - 0.1f
                    textValueWishTemperature.text = String.format("%.1f", newValue)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun setButtonControlHumidityListener() {
        with(binding) {
            buttonDropUpHumidity.setOnClickListener {
                val valueWishHumidity = textValueWishHumidity.text.toString().toFloat()
                if (valueWishHumidity < 100) {
                    val newValue = valueWishHumidity + 1f
                    textValueWishHumidity.text = String.format("%.0f", newValue)
                }
            }

            buttonDropDownHumidity.setOnClickListener {
                val valueWishHumidity = textValueWishHumidity.text.toString().toFloat()
                if (valueWishHumidity > 0) {
                    val newValue = valueWishHumidity - 1f
                    textValueWishHumidity.text = String.format("%.0f", newValue)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStateRoom(args.renting.roomId)
        fillInfoRoom()
        setupRecyclerView()
    }

    private fun fillInfoRoom() {
        with(binding) {
            textBeginRentingPanel.text = args.renting.beginDate.toString()
            textEndDate.text = args.renting.endDate.toString()
            textRentingPet.text = args.renting.petName
            textNumberRoomPanel.text = args.renting.numberRoom.toString()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerBlocksFood.adapter = panelAdapter
        binding.recyclerBlocksFood.layoutManager = LinearLayoutManager(requireContext())
    }
}