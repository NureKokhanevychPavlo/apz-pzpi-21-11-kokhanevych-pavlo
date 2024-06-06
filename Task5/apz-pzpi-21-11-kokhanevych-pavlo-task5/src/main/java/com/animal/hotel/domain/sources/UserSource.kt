package com.animal.hotel.domain.sources


import com.animal.hotel.domain.models.user.AutogenerateRequest
import com.animal.hotel.domain.models.user.DateRequest
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.domain.models.user.Hotel
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.models.user.User
import kotlinx.coroutines.flow.Flow

interface UserSource {

    suspend fun getPresentUser(email: String): User

    suspend fun getUserPhoto(userId: Int): ByteArray?

    suspend fun deleteUser(userId: Int)

    suspend fun saveUser(user: User, photoLink: String?)

    suspend fun getPetsOfUser(userId: Int): Flow<List<Pet>>

    suspend fun addPetsOfUser(listOfPets: List<Pet>)

    suspend fun getHistoryRenting(userId: Int): Flow<List<HistoryRentingResponse>>

    suspend fun getAllHotels(): Flow<List<Hotel>>

    suspend fun getRoomsByHotel(hotelId: Int): Flow<List<Room>>

    suspend fun addNewRentingForUser(listOfSchedules: List<Schedule>)

    suspend fun getAllFreeRoomByPeriod(hotelId: Int, dateRequest: DateRequest): Flow<List<Room>>

    suspend fun sendMessageToAdmin(userId: Int, message: String)

    suspend fun autogenerateSchedule(autogenerateRequest: AutogenerateRequest): Flow<List<ScheduleFeedResponse>>

    suspend fun getRoomById(roomId: Int): List<Room>

    suspend fun getScheduleFeed(rentId: Int): List<ScheduleFeedResponse>

    suspend fun updateTimeOfScheduleFeed(listOfResponseSchedule: List<ScheduleFeedResponse>)
}