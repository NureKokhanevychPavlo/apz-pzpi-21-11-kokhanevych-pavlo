package com.animal.hotel.presentation.ui.fragments.user.current_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentCurrentRentingBinding
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.current_list.adapter.CurrentRentingAdapter
import com.animal.hotel.presentation.ui.fragments.user.viewPager.ViewPagerFragmentDirections
import com.animal.hotel.presentation.ui.interfaces.CurrentRentingAdapterListener
import com.animal.hotel.presentation.ui.models.HistoryRentingResponseUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CurrentRentingFragment: BaseFragment<FragmentCurrentRentingBinding>(FragmentCurrentRentingBinding::inflate){

    private val viewModel: CurrentRentingViewModel by viewModels()

    private val currentRentingAdapter by lazy {
        CurrentRentingAdapter(
            listener = object :  CurrentRentingAdapterListener {
                override fun onItemClick(historyRentingResponse: HistoryRentingResponse) {
                    val direction =
                       ViewPagerFragmentDirections.actionViewPagerFragmentToPanelFragment(
                           HistoryRentingResponseUI.toHistoryRentingResponseUI(historyRentingResponse))
                    findNavController().navigate(direction)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHistoryRenting()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.currentRenting.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                currentRentingAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupRecyclerView() {
        binding.recyclerCurrentRenting.adapter = currentRentingAdapter
        binding.recyclerCurrentRenting.layoutManager = LinearLayoutManager(requireContext())
    }
}