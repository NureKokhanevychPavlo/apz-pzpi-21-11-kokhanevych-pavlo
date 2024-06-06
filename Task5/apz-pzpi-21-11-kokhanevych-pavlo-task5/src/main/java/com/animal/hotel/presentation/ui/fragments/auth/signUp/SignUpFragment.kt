package com.animal.hotel.presentation.ui.fragments.auth.signUp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.animal.hotel.R
import com.animal.hotel.databinding.FragmentRegisterBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import androidx.navigation.fragment.findNavController
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.presentation.utils.Constants.DATA_FORMAT_PATTERN
import com.animal.hotel.presentation.utils.SnackbarManager.showSnackbar
import com.animal.hotel.presentation.utils.extensions.addTextListener
import com.animal.hotel.presentation.utils.extensions.downloadAndPutPhoto
import com.animal.hotel.presentation.utils.observeEvent
import com.animal.hotel.utils.enums.UserType
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@AndroidEntryPoint
class SignUpFragment: BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: SignUpViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var urlAvatar: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initializeResultLauncher()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeShowAuthErrorMessageEvent()
    }

    private fun observeShowAuthErrorMessageEvent() = viewModel.showErrorMessageEvent.observeEvent(viewLifecycleOwner) {
        showSnackbar(requireView(), getString(it)) {findNavController().navigateUp()}
    }

    private fun initializeResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultForActivity ->
                downloadImage(resultForActivity)
            }
    }

    private fun downloadImage(result: androidx.activity.result.ActivityResult) {

        // Checks ending operation choosing photo.
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val selectedImageUri = data.data
                selectedImageUri?.run {
                    urlAvatar = getRealPathFromURI(selectedImageUri)?: ""
                    binding.imageAvatar.downloadAndPutPhoto(selectedImageUri.toString())
                }
            }
        }
    }

    override fun setListeners() {
        super.setListeners()
        addPhotoButtonListener()
        addCancelButtonListener()
        addRegisterButtonListener()
        binding.editEmail.addTextListener(viewModel::isEmailCorrect, getString(R.string.error_email))
        binding.editPassword.addTextListener( viewModel::isPasswordCorrect, getString(R.string.error_password))
        binding.editPhoneNumber.addTextListener(viewModel::isPhoneNumberCorrect, getString(R.string.error_phone_number))
        addAgainPasswordListener()
        addBirthDateListener()
    }

    private fun addPhotoButtonListener() {
        binding.buttonAddPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }
    }

    private fun addCancelButtonListener() {
        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addRegisterButtonListener() {
        binding.buttonRegister.setOnClickListener {
            if (isAllFieldCorrect()) {

                with(binding) {
                    val user: User = User(
                        fullName = editFullName.text.toString(),
                        password = editPassword.text.toString(),
                        email = editEmail.text.toString(),
                        birthDate = parseDate(editBirthDate.text.toString())?: LocalDate.now(),
                        phoneNumber = editPhoneNumber.text.toString(),
                        typeUser = UserType.CLIENT,
                        photoLink = urlAvatar
                    )

                    viewModel.registerUser(user)
                }
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

    private fun isAllFieldCorrect(): Boolean {
        with(binding) {
            return viewModel.isEmailCorrect(editEmail.text.toString()) &&
                    viewModel.isPasswordCorrect(editPassword.text.toString()) &&
                    viewModel.isPasswordsSame(editPassword.text.toString(), editAgainPassword.text.toString()) &&
                    viewModel.isPhoneNumberCorrect(editPhoneNumber.text.toString()) &&
                    (editFullName.text?.isNotEmpty() ?: "") as Boolean &&
                    viewModel.isOldEnough(parseDate(editBirthDate.text.toString()))
        }
    }

    private fun addAgainPasswordListener() {
        with(binding.editAgainPassword) {
            doOnTextChanged { text, _, _, _ ->
                error = if (!viewModel.isPasswordsSame( binding.editPassword.text.toString(), text.toString())) {
                    getString(R.string.error_password_same)
                } else {
                    null
                }
            }
        }
    }


    private fun addBirthDateListener() {
        with(binding.editBirthDate) {
            val birthDate = parseDate(text?.toString())
            if (birthDate != null) {
                error = if (birthDate.isEqual(LocalDate.MIN)) {
                    getString(R.string.error_date_format)
                } else if (!viewModel.isOldEnough(birthDate)) {
                    getString(R.string.error_old_enough)
                } else {
                    null
                }
            }
        }
    }

    private fun parseDate(text: String?): LocalDate? {
        return text?.run {
            try {
                LocalDate.parse(text, DateTimeFormatter.ofPattern(DATA_FORMAT_PATTERN))
            } catch (e: DateTimeParseException) {
                return null
            }
        }
    }
}