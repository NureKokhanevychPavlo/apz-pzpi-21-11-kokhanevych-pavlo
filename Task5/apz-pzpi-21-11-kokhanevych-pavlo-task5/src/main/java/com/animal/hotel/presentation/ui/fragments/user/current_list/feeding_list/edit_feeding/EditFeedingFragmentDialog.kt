package com.animal.hotel.presentation.ui.fragments.user.current_list.feeding_list.edit_feeding

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.animal.hotel.databinding.DialogCorrectScheduleBinding
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.presentation.utils.Constants
import com.animal.hotel.presentation.utils.Constants.DATA_TIME_FORMAT_PATTERN
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@AndroidEntryPoint
class EditFeedingFragmentDialog: DialogFragment() {

    private val viewModel: EditFeedingFragmentViewModel by viewModels()

    private var _binding: DialogCorrectScheduleBinding? = null
    private val binding get() = _binding!!

    private val args: EditFeedingFragmentDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCorrectScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        fillFields()
    }

    private fun fillFields() {
        with(binding) {
            editPortionCurrent.text =  Editable.Factory.getInstance().newEditable(args.feedingSchedule.portion.toString())
            val date = args.feedingSchedule.dateTime

            val formatter = DateTimeFormatter.ofPattern(DATA_TIME_FORMAT_PATTERN)
            val dateWithCorrectFormat = date.format(formatter)
            editFoodDateTimeCurrent.text = Editable.Factory.getInstance().newEditable(dateWithCorrectFormat)
        }
    }

    private fun isAllFieldCorrect(): Boolean {
        return viewModel.isDataTimeCorrect(parseDate(binding.editFoodDateTimeCurrent.text.toString())?: LocalDateTime.now()) &&
                binding.editPortionCurrent.text?.isNotEmpty() ?: false
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

    private fun setListeners() {
        binding.buttonBackCurrent.setOnClickListener{dismiss()}

        binding.buttonApplyCurrent.setOnClickListener{
            if (isAllFieldCorrect()) {
                viewModel.updateScheduleUseCase(ScheduleFeedResponse(
                    args.feedingSchedule.scheduleId,
                    parseDate(binding.editFoodDateTimeCurrent.text.toString())?: LocalDateTime.now(),
                     binding.editPortionCurrent.text.toString().toFloat(),
                      args.feedingSchedule.typeFood)
                )
                Toast.makeText(requireContext(), getString(com.animal.hotel.R.string.message_successfully), Toast.LENGTH_SHORT).show()
            }
        }
    }
}