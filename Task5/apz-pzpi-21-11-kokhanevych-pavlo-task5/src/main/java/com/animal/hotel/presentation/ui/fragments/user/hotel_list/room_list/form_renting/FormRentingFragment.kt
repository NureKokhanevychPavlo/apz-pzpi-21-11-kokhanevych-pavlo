package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.animal.hotel.databinding.FragmentFormRentingBinding
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.models.user.Rent
import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.adapter.FormRentingAdapter
import com.animal.hotel.presentation.ui.interfaces.FormRentingAdapterListener
import com.animal.hotel.presentation.utils.Constants
import com.animal.hotel.utils.enums.FoodType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class FormRentingFragment: BaseFragment<FragmentFormRentingBinding>(FragmentFormRentingBinding::inflate) {

    private val viewModel: FormRentingViewModel by viewModels()

    private val args: FormRentingFragmentArgs by navArgs()

    private val formRentingAdapter by lazy {
        FormRentingAdapter(
            listener = object : FormRentingAdapterListener {
                override fun onDeleteClick(schedule: Schedule) {
                   viewModel.deleteSchedule(schedule)
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.schedule.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                formRentingAdapter.submitList(data)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.pets.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                setupSpinner()
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupSpinner() {
        val petsName: List<String> = viewModel.pets.value.map { "${it.petId} ${it.name}" }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, petsName)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editTypeAnimal.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFragmentResultListener()
        viewModel.getPetsByUser()
        binding.textFullPriceFill.text = viewModel.calculateFullPrice(args.room.toRoom(), args.data).toString()
    }

    private fun setupRecyclerView() {
        binding.recyclerFeedingFill.adapter = formRentingAdapter
        binding.recyclerFeedingFill.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setListeners() {
        super.setListeners()
        binding.buttonBack.setOnClickListener{findNavController().navigateUp()}

        binding.buttonAutogenerate.setOnClickListener{
            val rent = Rent (
                beginDate = args.data.beginDate,
                endDate = args.data.endDate,
                room = args.room.toRoom(),
                pet = getPetFromSpinner()
            )
            viewModel.autogenerateScheduleUseCase(rent, args.numberOfBlock)
        }

        binding.buttonPay.setOnClickListener{
            viewModel.applyRenting()
        }

        binding.buttonAddFeedingFill.setOnClickListener{
            val direction =
                FormRentingFragmentDirections.actionFormRentingFragmentToAddFeedingDialog()
            findNavController().navigate(direction)
        }
    }

    private fun getPetFromSpinner(): Pet {
        val petId = binding.editTypeAnimal.selectedItem.toString().split(" ").first().toInt()
        return viewModel.pets.value.find { it.petId == petId } ?: viewModel.pets.value[0]
    }

    private fun setupFragmentResultListener() {
        parentFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            val dateTime = bundle.getString(DATE_TIME)
            val portion: Float = bundle.getString(PORTION)?.toFloat() ?: 0.0f
            val typeFood: FoodType = FoodType.valueOf(bundle.getString(TYPE_FOOD).toString())
            if (dateTime != null) {
                val formatter = DateTimeFormatter.ofPattern(Constants.DATA_TIME_FORMAT_PATTERN)
                val dataTimeParsed = LocalDateTime.parse(dateTime, formatter)
                viewModel.addSchedule(args.room.toRoom(), getPetFromSpinner(), args.data, dataTimeParsed, portion, typeFood)
            }
        }
    }

    companion object {
        const val REQUEST_KEY = "new_feeding"
        const val DATE_TIME = "date_time"
        const val PORTION = "portion"
        const val TYPE_FOOD = "type_food"
    }
}