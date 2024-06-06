package com.animal.hotel.presentation.ui.fragments.user.profile.edit_dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.animal.hotel.R
import com.animal.hotel.databinding.DialogEditProfileBinding
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.presentation.ui.fragments.auth.signUp.SignUpViewModel
import com.animal.hotel.presentation.ui.fragments.user.profile.ProfileViewModel
import com.animal.hotel.presentation.utils.Constants
import com.animal.hotel.presentation.utils.extensions.downloadAndPutPhoto
import com.animal.hotel.utils.enums.UserType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@AndroidEntryPoint
class ProfileEditDialog: DialogFragment() {

    private val viewModel: ProfileEditViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var urlAvatar: String? = null

    private var _binding: DialogEditProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeResultLauncher()
        setListeners()
        setObserverUser()
        setObserverPhoto()
        viewModel.getUser()
        viewModel.getUserPhoto()
    }

    private fun setObserverUser() {
        viewModel.user.flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { data ->
                data?.run {
                    with(binding) {
                        editEmail.text = Editable.Factory.getInstance().newEditable(data.email)
                        editFullName.text = Editable.Factory.getInstance().newEditable(data.fullName)
                        editPhone.text = Editable.Factory.getInstance().newEditable(data.phoneNumber)
                        editTextBirth.text = Editable.Factory.getInstance().newEditable(data.birthDate.toString())
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

    private fun setListeners() {
        binding.buttonApply.setOnClickListener {
            if (isAllFieldCorrect()) {
                saveUser()
            }
        }

        binding.buttonAddPhoto.setOnClickListener { openPhotoList() }

        binding.buttonBack.setOnClickListener { dismiss() }
    }

    private fun isAllFieldCorrect(): Boolean {
        with(binding) {
            return viewModel.isEmailCorrect(editEmail.text.toString()) &&
                    viewModel.isPhoneNumberCorrect(editPhone.text.toString()) &&
                    (editFullName.text?.isNotEmpty() ?: "") as Boolean &&
                    viewModel.isOldEnough(parseDate(editTextBirth.text.toString()))
        }
    }

    private fun saveUser() {
        val user = viewModel.user.value?.let {
            parseDate(binding.editTextBirth.text.toString())?.let { birthDate ->
                User(
                    userId = it.userId,
                    fullName = binding.editFullName.text.toString(),
                    email = binding.editEmail.text.toString(),
                    password = it.password,
                    phoneNumber = binding.editPhone.text.toString(),
                    birthDate = birthDate,
                    typeUser = UserType.CLIENT,
                    photoLink = urlAvatar.toString()
                )
            }
        }
        user?.let { viewModel.saveUser(it) }
    }

    private fun parseDate(text: String?): LocalDate? {
        return text?.run {
            try {
                LocalDate.parse(this, DateTimeFormatter.ofPattern(Constants.DATA_FORMAT_PATTERN))
            } catch (e: DateTimeParseException) {
                null
            }
        }
    }

    private fun openPhotoList() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private fun initializeResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                downloadImage(result)
            }
    }

    private fun downloadImage(result: androidx.activity.result.ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { selectedImageUri ->
                urlAvatar = getRealPathFromURI(selectedImageUri) ?: ""
                binding.imageAvatar.downloadAndPutPhoto(selectedImageUri.toString())
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return it.getString(columnIndex)
            }
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
