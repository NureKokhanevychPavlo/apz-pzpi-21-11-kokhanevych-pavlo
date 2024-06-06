package com.animal.hotel.presentation.ui.fragments.user.history_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentHistoryBinding
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
class HistoryFragment: BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private val viewModel: HistoryViewModel by viewModels()

    private val historyAdapter by lazy {
        CurrentRentingAdapter(
            listener = object : CurrentRentingAdapterListener {
                override fun onItemClick(historyRentingResponse: HistoryRentingResponse) {
                    val direction =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToDetailRentingFragment2(
                            HistoryRentingResponseUI.toHistoryRentingResponseUI(historyRentingResponse)
                        )
                    findNavController().navigate(direction)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHistory()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.history.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                historyAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupRecyclerView() {
        binding.recyclerHistoryRenting.adapter = historyAdapter
        binding.recyclerHistoryRenting.layoutManager = LinearLayoutManager(requireContext())
    }
}