package com.animal.hotel.presentation.ui.fragments.user.hotel_list

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentListHotelBinding
import com.animal.hotel.domain.models.user.Hotel
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.adapter.HotelAdapter
import com.animal.hotel.presentation.ui.fragments.user.viewPager.ViewPagerFragmentDirections
import com.animal.hotel.presentation.ui.interfaces.HotelAdapterListener
import com.animal.hotel.presentation.ui.models.HotelUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListHotelFragment: BaseFragment<FragmentListHotelBinding>(FragmentListHotelBinding::inflate),
SearchView.OnQueryTextListener{

    private val viewModel: ListHotelViewModel by viewModels()

    private val hotelAdapter by lazy {
        HotelAdapter(
            listener = object : HotelAdapterListener {
                override fun onItemClick(hotel: Hotel) {
                    val direction =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToListRoomFragment(
                            HotelUI.toHotelUI(hotel))
                    findNavController().navigate(direction)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllHotels()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.hotels.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                hotelAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setListeners() {
        super.setListeners()
        addButtonSearchListener()
        binding.editTextSearch.setIconifiedByDefault(false)
        binding.editTextSearch.setOnQueryTextListener(this@ListHotelFragment)
    }

    private fun addButtonSearchListener() {
        binding.buttonSearch.setOnClickListener {
            toggleVisibility(binding.textHeader)
            toggleVisibility(binding.editTextSearch)
        }
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    private fun setupRecyclerView() {
        binding.recyclerHotels.adapter = hotelAdapter
        binding.recyclerHotels.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchNotes(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchNotes(newText)
        return true
    }

    private fun searchNotes(query: String?) {
        query?.let {
            lifecycleScope.launch {
                viewModel.searchNotesByName(query).collect { notes ->
                    hotelAdapter.submitList(notes)
                }
            }
        }
    }
}