package com.animal.hotel.presentation.ui.fragments.user.pets_list


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentListPetsBinding
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.pets_list.adapter.PetsAdapter
import com.animal.hotel.presentation.ui.fragments.user.pets_list.add_pet.AddPetDialog
import com.animal.hotel.presentation.ui.fragments.user.viewPager.ViewPagerFragmentDirections
import com.animal.hotel.presentation.ui.interfaces.PetAdapterListener
import com.animal.hotel.presentation.ui.models.PetUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListPetsFragment: BaseFragment<FragmentListPetsBinding>(FragmentListPetsBinding::inflate) {

    private val viewModel: ListPetsViewModel by viewModels()

    private val petAdapter by lazy {
        PetsAdapter(
            listener = object : PetAdapterListener {
                override fun onItemClick(pet: Pet) {
                    val direction =
                        ViewPagerFragmentDirections.actionViewPagerFragmentToEditPetFragment(PetUI.toPetUI(pet))
                    findNavController().navigate(direction)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPets()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPets()
        viewModel.pets.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                petAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonAddPet.setOnClickListener {
            val dialogAddUser = AddPetDialog()
            dialogAddUser.show(parentFragmentManager, ADD_PET_TAG)
        }
        binding.buttonReload.setOnClickListener {
            viewModel.getPets()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerPets.adapter = petAdapter
        binding.recyclerPets.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        private const val ADD_PET_TAG = "Add pets"
    }
}