package com.animal.hotel.presentation.ui.fragments.user.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.animal.hotel.databinding.FragmentProfileBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.fragments.user.profile.edit_dialog.ProfileEditDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment: BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate){

    private val viewModel: ProfileViewModel by viewModels()

    override fun setListeners() {
        super.setListeners()
        addEditButtonListener()
        addDeleteButtonListener()
    }

    private fun addEditButtonListener() {
       binding.buttonEditProfile.setOnClickListener {
           val dialogAddUser = ProfileEditDialog()
           dialogAddUser.show(parentFragmentManager, EDIT_USER_TAG)
       }
    }

    private fun addDeleteButtonListener() {
        binding.buttonDelete.setOnClickListener{
            viewModel.deleteUser()
            activity?.finish()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
        viewModel.getUserPhoto()
    }

    override fun onStart() {
        super.onStart()
        setObserverUserName()
        setObserverPhoto()
    }

    private fun setObserverUserName() {
        viewModel.user.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                data?.run {
                    with(binding) {
                        editEmail.text = data.email
                        editFullName.text = data.fullName
                        editPhoneNumber.text = data.phoneNumber
                        editBirthDate.text = data.birthDate.toString()
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setObserverPhoto() {
        viewModel.userPhoto.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                val userPhotoBitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                binding.imageAvatar.setImageBitmap(userPhotoBitmap)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        private const val EDIT_USER_TAG = "EditUserFragment"
    }
}