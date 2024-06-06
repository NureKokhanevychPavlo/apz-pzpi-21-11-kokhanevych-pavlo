package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.filter_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.animal.hotel.R
import com.animal.hotel.databinding.DialogFilterRoomBinding
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.ListRoomFragment.Companion.BEGIN_DATE
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.ListRoomFragment.Companion.END_DATE
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.ListRoomFragment.Companion.REQUEST_KEY
import com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.ListRoomViewModel
import com.animal.hotel.presentation.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@AndroidEntryPoint
class FilterRoomDialog: DialogFragment() {

    private val viewModel: ListRoomViewModel by viewModels()

    private var _binding: DialogFilterRoomBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFilterRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.buttonApply.setOnClickListener{
            val beginDate = parseDate(binding.editRentingBeginDate.text.toString())
            val endDate = parseDate(binding.editRentingEndDate.text.toString())
            if (viewModel.isRentingDatesCorrect(beginDate,endDate)) {
                val result = Bundle().apply {
                    putString(BEGIN_DATE, beginDate.toString())
                    putString(END_DATE, endDate.toString())
                }
                parentFragmentManager.setFragmentResult(REQUEST_KEY, result)
                Toast.makeText(requireContext(), getString(R.string.message_successfully), Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonBack.setOnClickListener {dismiss()}
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