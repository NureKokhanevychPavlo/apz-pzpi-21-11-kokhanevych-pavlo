package com.animal.hotel.presentation.ui.fragments.user.pets_list.add_pet

import android.R
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.animal.hotel.databinding.DialogAddPetBinding
import com.animal.hotel.presentation.utils.extensions.downloadAndPutPhoto
import com.animal.hotel.utils.enums.PetType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPetDialog: DialogFragment() {

    private val viewModel: AddPetViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var urlAvatar: String? = null

    private var _binding: DialogAddPetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAddPetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()
        initializeResultLauncher()
        setupSpinner()
        setListeners()
    }

    private fun setupSpinner() {
        val petTypes = PetType.entries.map { it.name }
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, petTypes)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.editTypeAnimal.adapter = adapter
    }

    private fun setListeners() {
        binding.buttonApply.setOnClickListener{
            if (isPetFieldsCorrect()) {
                savePet()
            }
        }

        binding.buttonAddPhoto.setOnClickListener {openPhotoList()}

        binding.buttonBack.setOnClickListener{dismiss()}
    }

    private fun isPetFieldsCorrect(): Boolean {
        with(binding) {
            return textNamePet.text.isNotEmpty() &&
                    textAgePet.text.isNotEmpty() &&
                    textWeightPet.text.isNotEmpty()
        }
    }

    private fun savePet() {
        with(binding) {
                viewModel.savePet(
                    editNamePet.text.toString(),
                    editAgePet.text.toString().toInt(),
                    editWeightPet.text.toString().toFloat(),
                    PetType.valueOf(editTypeAnimal.selectedItem.toString()),
                    editDescription.text.toString(),
                    urlAvatar?: "",
                )
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