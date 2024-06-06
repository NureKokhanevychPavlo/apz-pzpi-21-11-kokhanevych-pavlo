package com.animal.hotel.presentation.ui.fragments.auth.signIn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.animal.hotel.R
import com.animal.hotel.databinding.FragmentAuthBinding
import com.animal.hotel.domain.models.authentication.SignInRequest
import com.animal.hotel.presentation.ui.activities.MainActivity
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.utils.Constants
import com.animal.hotel.presentation.utils.extensions.addTextListener
import com.animal.hotel.presentation.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class SignInFragment: BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: SignInViewModel by viewModels()

    private val sharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(Constants.AUTO_LOG, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoLogin()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeShowAuthErrorMessageEvent()
        observeToken()
    }

    private fun observeShowAuthErrorMessageEvent() = viewModel.showErrorMessageEvent.observeEvent(viewLifecycleOwner) {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun observeToken() {
        viewModel.isTokenPresent.observe(viewLifecycleOwner) {
            if (it == true) {
                startMainActivity()
            }
        }
    }

    private fun autoLogin() {
        if (sharedPreferences.all.isNotEmpty()) {
            binding.editEmail.setText(sharedPreferences.getString(Constants.EMAIL, null))
            binding.editPassword.setText(sharedPreferences.getString(Constants.PASSWORD, null))
        }
    }

    override fun setListeners() {
        binding.editEmail.addTextListener(viewModel::isEmailCorrect, getString(R.string.error_email))
        binding.editPassword.addTextListener(viewModel::isPasswordCorrect, getString(R.string.error_password))
        addRegisterButtonListener()
        binding.signInButton.setOnClickListener {
            signInUser()
        }
    }

    private fun addRegisterButtonListener() {
        with(binding.buttonRegisterUser) {
            setOnClickListener {
                it.findNavController().navigate(R.id.action_authFragment_to_registerFragment)
            }
        }
    }

    private fun signInUser() {
        with(binding) {
            if (viewModel.isPasswordCorrect(editPassword.text.toString())
                && viewModel.isEmailCorrect(editEmail.text.toString())) {
                runBlocking {
                    launch {
                        viewModel.signIn(SignInRequest(editEmail.text.toString(), editPassword.text.toString()))
                    }
                }
            }
        }
    }

    private fun startMainActivity() {
            saveAutoLog()
            val intentToMain = Intent(requireActivity(), MainActivity::class.java).apply {
                putExtra(Constants.EMAIL, binding.editEmail.text)
            }

            val options = ActivityOptionsCompat.makeCustomAnimation(
                requireActivity(),
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )

            startActivity(intentToMain, options.toBundle())
    }

    private fun saveAutoLog() {
        with(binding) {
            if (checkboxRemember.isChecked) {
                sharedPreferences.edit()
                    .putString(Constants.PASSWORD, editPassword.text.toString())
                    .putString(Constants.EMAIL, editEmail.text.toString())
                    .apply()
            } else {
                sharedPreferences.edit().clear().apply()
            }
        }
    }
}