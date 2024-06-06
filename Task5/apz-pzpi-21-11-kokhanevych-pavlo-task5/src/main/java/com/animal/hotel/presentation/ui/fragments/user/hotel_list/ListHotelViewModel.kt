package com.animal.hotel.presentation.ui.fragments.user.hotel_list

import androidx.lifecycle.viewModelScope
import com.animal.hotel.domain.models.user.Hotel
import com.animal.hotel.domain.useCases.userCases.GetAllHotelsUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListHotelViewModel @Inject constructor(
    private val getHotelUseCase: GetAllHotelsUseCase
): BaseViewModel() {

    private val _hotels = MutableStateFlow<List<Hotel>>(emptyList())
    val hotels: StateFlow<List<Hotel>> = _hotels

    fun getAllHotels() {
        viewModelScope.safeLaunch {
            _hotels.value = getHotelUseCase().first()
        }
    }

    fun searchNotesByName(name: String): Flow<List<Hotel>> {
        return _hotels.map { list ->
            list.filter {
                it.name.contains(name, ignoreCase = true)
            }
        }
    }
}