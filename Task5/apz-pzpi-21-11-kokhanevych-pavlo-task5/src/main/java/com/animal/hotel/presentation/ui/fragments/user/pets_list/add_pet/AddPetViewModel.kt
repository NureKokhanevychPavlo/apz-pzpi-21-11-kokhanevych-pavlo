package com.animal.hotel.presentation.ui.fragments.user.pets_list.add_pet

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.useCases.userCases.AddPetsUserUseCase
import com.animal.hotel.domain.useCases.userCases.GetUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import com.animal.hotel.utils.enums.PetType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val addPetsUserUseCase: AddPetsUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    lateinit var user: User

    fun savePet(name: String, age: Int, weight: Float, typeAnimal: PetType, description: String?, photoLink: String) = viewModelScope.safeLaunch {
        delay(2000)
        val pet = Pet(
            name = name,
            age =  age,
            weight = weight,
            typePet = typeAnimal,
            description = description,
            photoLink = photoLink,
            user = user
        )
        addPetsUserUseCase(listOf(pet))
    }

      fun getUser() = viewModelScope.safeLaunch {
        val email = appSettings.getEmail()
        email?.run {
            user = getUserUseCase(email)
        }
    }
}