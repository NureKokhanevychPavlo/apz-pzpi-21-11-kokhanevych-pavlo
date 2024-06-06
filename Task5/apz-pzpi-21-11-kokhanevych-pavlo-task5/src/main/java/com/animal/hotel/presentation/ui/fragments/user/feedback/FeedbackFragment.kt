package com.animal.hotel.presentation.ui.fragments.user.feedback


import android.widget.Toast
import androidx.fragment.app.viewModels
import com.animal.hotel.R
import com.animal.hotel.databinding.FragmentFeedbackBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedbackFragment: BaseFragment<FragmentFeedbackBinding>(FragmentFeedbackBinding::inflate){

    private val viewModel: FeedbackViewModel by viewModels()

    override fun setListeners() {
        super.setListeners()
        addButtonSendListener()
    }

    private fun addButtonSendListener() {
        with(binding) {
            buttonSend.setOnClickListener{
                viewModel.sendMessage(textMessage.text.toString())
                textMessage.text?.clear()
                Toast.makeText(requireContext(), getString(R.string.message_successfully), Toast.LENGTH_SHORT).show()
            }
        }
    }
}