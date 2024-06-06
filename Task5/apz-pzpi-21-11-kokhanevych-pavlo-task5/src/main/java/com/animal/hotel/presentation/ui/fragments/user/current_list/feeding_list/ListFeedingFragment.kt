package com.animal.hotel.presentation.ui.fragments.user.current_list.feeding_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentListFeedingBinding
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.current_list.feeding_list.adapter.FeedingAdapter
import com.animal.hotel.presentation.ui.interfaces.FeedingAdapterListener
import com.animal.hotel.presentation.ui.models.ScheduleFeedResponseUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListFeedingFragment: BaseFragment<FragmentListFeedingBinding>(FragmentListFeedingBinding::inflate) {

    private val viewModel: ListFeedingViewModel by viewModels()

    private val args: ListFeedingFragmentArgs by navArgs()

    private val feedingAdapter by lazy {
        FeedingAdapter(
            listener = object : FeedingAdapterListener {
                override fun onItemClick(scheduleFeedResponse: ScheduleFeedResponse) {
                    val direction =
                       ListFeedingFragmentDirections.actionListFeedingFragmentToEditFeedingFragmentDialog(ScheduleFeedResponseUI.toScheduleFeedResponseUI(scheduleFeedResponse))
                    findNavController().navigate(direction)
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.scheduleFeeding.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                feedingAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScheduleFeeding(args.rentId)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerFoodSchedule.adapter = feedingAdapter
        binding.recyclerFoodSchedule.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonBack.setOnClickListener{findNavController().navigateUp()}
    }
}