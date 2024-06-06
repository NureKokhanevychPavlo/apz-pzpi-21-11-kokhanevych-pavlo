package com.animal.hotel.presentation.ui.fragments.user.pets_list.edit_pet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.animal.hotel.R
import com.animal.hotel.databinding.FragmentEditPetBinding
import com.animal.hotel.presentation.ui.base.BaseFragment
import com.animal.hotel.presentation.ui.models.PetUI
import com.animal.hotel.presentation.utils.extensions.downloadAndPutPhoto
import com.animal.hotel.utils.enums.PetType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPetFragment: BaseFragment<FragmentEditPetBinding>(FragmentEditPetBinding::inflate) {

    private val args: EditPetFragmentArgs by navArgs()

    private val viewModel: EditPetViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private var urlAvatar: String? = null

    override fun setListeners() {
        super.setListeners()
        with(binding) {
            buttonApply.setOnClickListener{
                if (isPetFieldsCorrect()) {
                    savePet()
                }
            }
            buttonBack.setOnClickListener{ findNavController().navigateUp()}
            buttonAddPhoto.setOnClickListener{ openPhotoList()}
        }
    }

    private fun isPetFieldsCorrect(): Boolean {
        with(binding) {
            return textNamePet.text.isNotEmpty() &&
                    textAgePet.text.isNotEmpty() &&
                    textWeightPet.text.isNotEmpty()
        }
    }

    private fun savePet() {
        val petUI = PetUI(
            petId =  args.pet.petId,
            name =  binding.editNamePet.text.toString(),
            age =  binding.editAgePet.text.toString().toInt(),
            weight = binding.editWeightPet.text.toString().toFloat(),
            typePet = PetType.valueOf(binding.editTypeAnimal.selectedItem.toString()),
            description = binding.editDescription.text.toString(),
            photoLink = urlAvatar,
            user = args.pet.user
        )
        viewModel.savePet(petUI)
        Toast.makeText(requireContext(), getString(R.string.message_successfully), Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeResultLauncher()
        setupSpinner()
        fillPets()
    }

    private fun fillPets() {
        with(binding) {
            editNamePet.text = Editable.Factory.getInstance().newEditable(args.pet.name)
            editAgePet.text= Editable.Factory.getInstance().newEditable(args.pet.age.toString())
            editWeightPet.text = Editable.Factory.getInstance().newEditable(args.pet.weight.toString())
            setInitialSpinnerValue()
            editDescription.text = Editable.Factory.getInstance().newEditable(args.pet.description?: "")
        }
    }

    private fun setInitialSpinnerValue() {
        val petType = args.pet.typePet.name
        val petTypes = PetType.entries.map { it.name }
        val position = petTypes.indexOf(petType)
        if (position >= 0) {
            binding.editTypeAnimal.setSelection(position)
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

    private fun setupSpinner() {
        val petTypes = PetType.entries.map { it.name }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, petTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.editTypeAnimal.adapter = adapter
    }
}