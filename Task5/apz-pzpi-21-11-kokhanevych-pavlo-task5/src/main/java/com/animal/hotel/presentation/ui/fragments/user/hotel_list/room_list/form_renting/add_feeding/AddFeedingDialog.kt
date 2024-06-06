package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.add_feeding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.animal.hotel.R
import com.animal.hotel.databinding.DialogFoodScheduleBinding
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.FormRentingFragment.Companion.DATE_TIME
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.FormRentingFragment.Companion.PORTION
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.FormRentingFragment.Companion.TYPE_FOOD
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.FormRentingFragment.Companion.REQUEST_KEY
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting.FormRentingViewModel
import com.animal.hotel.presentation.utils.Constants
import com.animal.hotel.utils.enums.FoodType
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@AndroidEntryPoint
class AddFeedingDialog: DialogFragment() {

    private val viewModel: FormRentingViewModel by viewModels()

    private var _binding: DialogFoodScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFoodScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setListeners()
    }

    private fun setListeners() {
        binding.buttonBack.setOnClickListener{dismiss()}

        binding.buttonApply.setOnClickListener{
            if (isAllFieldCorrect()) {
                val result = Bundle().apply {
                    putString(DATE_TIME, binding.editFoodDateTime.text.toString())
                    putString(PORTION, binding.editPortion.text.toString())
                    putString(TYPE_FOOD, binding.editTypeFood.selectedItem.toString())
                }
                parentFragmentManager.setFragmentResult(REQUEST_KEY, result)
                Toast.makeText(requireContext(), getString(R.string.message_successfully), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSpinner() {
        val foodTypes = FoodType.entries.map { it.name }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, foodTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editTypeFood.adapter = adapter
    }

    private fun isAllFieldCorrect(): Boolean {
        return viewModel.isDataTimeCorrect(parseDate(binding.editFoodDateTime.text.toString())?: LocalDateTime.now()) &&
                binding.editPortion.text?.isNotEmpty() ?: false
    }

    private fun parseDate(text: String?): LocalDateTime? {
        return text?.run {
            try {
                LocalDateTime.parse(this, DateTimeFormatter.ofPattern(Constants.DATA_TIME_FORMAT_PATTERN))
            } catch (e: DateTimeParseException) {
                null
            }
        }
    }
}