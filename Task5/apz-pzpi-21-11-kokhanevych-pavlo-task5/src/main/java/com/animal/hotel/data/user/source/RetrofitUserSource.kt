package com.animal.hotel.data.user.source

import android.util.Log
import com.animal.hotel.data.ExceptionHandler
import com.animal.hotel.data.authentication.source.RetrofitAuthenticationSource
import com.animal.hotel.data.user.api.UserApi
import com.animal.hotel.data.user.entities.AutogenerateRequestEntity
import com.animal.hotel.data.user.entities.DateRequestEntity
import com.animal.hotel.data.user.entities.PetEntity
import com.animal.hotel.data.user.entities.ScheduleEntity
import com.animal.hotel.data.user.entities.ScheduleFeedResponseEntity
import com.animal.hotel.data.utils.LocalDateConverter
import com.animal.hotel.domain.models.user.AutogenerateRequest
import com.animal.hotel.domain.models.user.DateRequest
import com.animal.hotel.domain.models.user.HistoryRentingResponse
import com.animal.hotel.domain.models.user.Hotel
import com.animal.hotel.domain.models.user.Pet
import com.animal.hotel.domain.models.user.Room
import com.animal.hotel.domain.models.user.Schedule
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.sources.UserSource
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitUserSource @Inject constructor(
    private val userApi: UserApi
): ExceptionHandler(), UserSource {

    override suspend fun getPresentUser(email: String): User = wrapRetrofitExceptions {
        userApi.getPresentUser(email).toUser()
    }

    override suspend fun getUserPhoto(userId: Int): ByteArray? = wrapRetrofitExceptions {
        try {
            val response = userApi.getUserPhoto(userId)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.bytes()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun deleteUser(userId: Int) = wrapRetrofitExceptions {
        userApi.deleteUser(userId)
    }


    override suspend fun saveUser(user: User, photoLink: String?) = wrapRetrofitExceptions {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateConverter())
            .create()

        val userJson = gson.toJson(user)
        val userRequestBody = RequestBody.create(MediaType.parse(RetrofitAuthenticationSource.JSON_TYPE_MEDIA), userJson)
        val body = MultipartBody.Part.createFormData(RetrofitAuthenticationSource.USER, null, userRequestBody)
        userApi.saveUser(body, createMultipartFromUri(photoLink ?: ""))
    }

    private fun createMultipartFromUri(selectedImageUri: String): MultipartBody.Part? {
        val file = File(selectedImageUri)
        if (!file.exists()) {
            Log.e("File Error", "File does not exist: $selectedImageUri")
            return null
        }
        val requestFile = RequestBody.create(MultipartBody.FORM, file)
        val bodyTest = MultipartBody.Part.createFormData(RetrofitAuthenticationSource.PHOTO, file.name, requestFile)

        return bodyTest
    }

    override suspend fun getPetsOfUser(userId: Int): Flow<List<Pet>> = wrapRetrofitExceptions {
        val pets = userApi.getPetsOfUser(userId).map { it.toPet() }
        flowOf(pets)
    }

    override suspend fun addPetsOfUser(listOfPets: List<Pet>) = wrapRetrofitExceptions {
        userApi.addPetsOfUser(listOfPets.map { PetEntity.toPetEntity(it)})
    }

    override suspend fun getHistoryRenting(userId: Int): Flow<List<HistoryRentingResponse>> = wrapRetrofitExceptions {
        val historyRentingResponse = userApi.getHistoryRenting(userId).map { it.toHistoryRentingResponse()}
        flowOf(historyRentingResponse)
    }

    override suspend fun getAllHotels(): Flow<List<Hotel>> = wrapRetrofitExceptions {
        val hotels = userApi.getAllHotels().map { it.toHotel() }
        flowOf(hotels)
    }

    override suspend fun getRoomsByHotel(hotelId: Int): Flow<List<Room>> = wrapRetrofitExceptions {
        flowOf(userApi.getRoomsByHotel(hotelId).map { it.toRoom() })
    }

    override suspend fun addNewRentingForUser(listOfSchedules: List<Schedule>) = wrapRetrofitExceptions {
        userApi.addNewRentingForUser(listOfSchedules.map { ScheduleEntity.toScheduleEntity(it) })
    }

    override suspend fun getAllFreeRoomByPeriod(hotelId: Int, dateRequest: DateRequest): Flow<List<Room>> = wrapRetrofitExceptions {
        flowOf(userApi.getAllFreeRoomByPeriod(hotelId, DateRequestEntity.toDateRequestEntity(dateRequest)).map { it.toRoom() })
    }

    override suspend fun sendMessageToAdmin(userId: Int, message: String)  = wrapRetrofitExceptions {
        userApi.sendMessageToAdmin(userId, message)
    }

    override suspend fun autogenerateSchedule(autogenerateRequest: AutogenerateRequest): Flow<List<ScheduleFeedResponse>> = wrapRetrofitExceptions {
        flowOf(userApi.autogenerateSchedule( AutogenerateRequestEntity.toAutogenerateRequestEntity(autogenerateRequest)).map { it.toScheduleFeedResponse() })
    }

    override suspend fun getRoomById(roomId: Int): List<Room>  = wrapRetrofitExceptions {
        userApi.getRoomById(roomId).map { it.toRoom() }
    }

    override suspend fun getScheduleFeed(rentId: Int): List<ScheduleFeedResponse> = wrapRetrofitExceptions {
        userApi.getScheduleFeed(rentId).map { it.toScheduleFeedResponse() }
    }

    override suspend fun updateTimeOfScheduleFeed(listOfResponseSchedule: List<ScheduleFeedResponse>) = wrapRetrofitExceptions {
        userApi.updateTimeOfScheduleFeed(listOfResponseSchedule.map { ScheduleFeedResponseEntity.toScheduleFeedResponseEntity(it) })
    }

    companion object {
        private const val USER = "user"
        private const val PHOTO = "photo"
        private const val JSON_TYPE_MEDIA = "application/json"
    }
}