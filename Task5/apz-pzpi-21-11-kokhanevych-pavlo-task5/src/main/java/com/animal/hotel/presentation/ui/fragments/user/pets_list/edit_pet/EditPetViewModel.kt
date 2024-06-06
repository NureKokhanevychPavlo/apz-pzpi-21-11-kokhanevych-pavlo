package com.animal.hotel.presentation.ui.fragments.user.pets_list.edit_pet

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.useCases.userCases.AddPetsUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import com.animal.hotel.presentation.ui.models.PetUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPetViewModel @Inject constructor(
    private val addPetsUserUseCase: AddPetsUserUseCase,
): BaseViewModel() {

    fun savePet(pet: PetUI) = viewModelScope.safeLaunch {
        addPetsUserUseCase(listOf(pet.toPet()))
    }
}