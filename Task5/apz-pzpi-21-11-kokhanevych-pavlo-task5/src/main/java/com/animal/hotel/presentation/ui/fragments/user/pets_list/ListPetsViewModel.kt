package com.animal.hotel.presentation.ui.fragments.user.pets_list

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.useCases.userCases.GetPetsUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class ListPetsViewModel @Inject constructor(
    private val getPetsUserUseCase: GetPetsUserUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    fun getPets() {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.run {
                _pets.value = getPetsUserUseCase(userId).first()
            }
        }
    }
}