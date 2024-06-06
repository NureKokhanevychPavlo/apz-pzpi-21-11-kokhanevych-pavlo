package com.animal.hotel.presentation.ui.fragments.user.hotel_list.room_list.form_renting

import androidx.lifecycle.viewModelScope
import com.animal.hotel.data.utils.ValidationFood
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.user.AutogenerateRequest
import com.animal.hotel.domain.models.user.Diet
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.models.user.Rent
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.domain.useCases.userCases.AddRentingUseCase
import com.animal.hotel.domain.useCases.userCases.AutogenerateScheduleUseCase
import com.animal.hotel.domain.useCases.userCases.GetPetsUserUseCase
import com.animal.hotel.presentation.ui.base.BaseViewModel
import com.animal.hotel.presentation.ui.models.DateRequestUI
import com.animal.hotel.utils.enums.FoodType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class FormRentingViewModel @Inject constructor(
    private val autogenerateScheduleUseCase: AutogenerateScheduleUseCase,
    private val addRentingUseCase: AddRentingUseCase,
    private val getPetsUserUseCase: GetPetsUserUseCase,
    private val appSettings: AppSettings
): BaseViewModel() {

    private val _schedule = MutableStateFlow<List<Schedule>>(emptyList())
    val schedule: StateFlow<List<Schedule>> = _schedule

    private val _pets = MutableStateFlow<List<Pet>>(emptyList())
    val pets: StateFlow<List<Pet>> = _pets

    fun applyRenting() {
        viewModelScope.safeLaunch {
            addRentingUseCase(schedule.value)
        }
    }

    fun addSchedule(room: Room, pet: Pet, dateRequest: DateRequestUI, dataTime: LocalDateTime, portion: Float, typeFood: FoodType) {
        val rent = Rent(
            beginDate = dateRequest.beginDate,
            endDate =  dateRequest.endDate,
            room = room,
            pet = pet
        )

        val diet: Diet = Diet(
            portion = portion,
            typeFood = typeFood,
            rent = rent
            )

        val schedule: Schedule = Schedule(
            dateTime = dataTime,
            diet = diet
        )

        val currentList = _schedule.value.toMutableList()
        currentList.add(schedule)
        _schedule.value = currentList
    }

    fun autogenerateScheduleUseCase(rent: Rent, numberOfBlock: Int) {
        viewModelScope.safeLaunch {
            val schedule =
                autogenerateScheduleUseCase(AutogenerateRequest(rent, numberOfBlock)).first()
            _schedule.value = schedule
        }
    }

    fun getPetsByUser() {
        viewModelScope.safeLaunch {
            val userId = appSettings.getUserId()
            userId?.run {
                _pets.value = getPetsUserUseCase(userId).first()
            }
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.safeLaunch {
            val currentList = _schedule.value.toMutableList()
            currentList.remove(schedule)
            _schedule.value = currentList
        }
    }

    fun calculateFullPrice(room: Room, dateRequest: DateRequestUI): Int {
        val duration = Duration.between(dateRequest.beginDate, dateRequest.endDate).toHours()
        return (room.priceHour * duration).toInt()
    }

    fun isDataTimeCorrect(data: LocalDateTime): Boolean {
        return ValidationFood.isDateOfFeedingCorrect(data)
    }
}