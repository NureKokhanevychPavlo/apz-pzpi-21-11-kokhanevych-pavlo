package com.animal.hotel.data.user.api

import com.animal.hotel.data.user.entities.AutogenerateRequestEntity
import com.animal.hotel.data.user.entities.DateRequestEntity
import com.animal.hotel.data.user.entities.HistoryRentingResponseEntity
import com.animal.hotel.data.user.entities.HotelEntity
import com.animal.hotel.data.user.entities.PetEntity
import com.animal.hotel.data.user.entities.RoomEntity
import com.animal.hotel.data.user.entities.ScheduleEntity
import com.animal.hotel.data.user.entities.ScheduleFeedResponseEntity
import com.animal.hotel.data.user.entities.UserEntity
import com.animal.hotel.data.user.entities.UserRequestEntity
import com.animal.hotel.domain.models.user.ScheduleFeedResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("user/account/{email}")
    suspend fun getPresentUser(@Path("email") email: String): UserEntity

    @GET("user/photo/{userId}")
    suspend fun getUserPhoto(@Path("userId") userId: Int): Response<ResponseBody>

    @POST("user/account/{userId}/delete")
    suspend fun deleteUser(@Path("userId") userId: Int)

    @Multipart
    @POST("user/account/save")
    suspend fun saveUser(@Part user: MultipartBody.Part, @Part upload: MultipartBody.Part?)

    @GET("user/{userId}/listOfPets")
    suspend fun getPetsOfUser(@Path("userId") userId: Int): List<PetEntity>

    @POST("user/addPets")
    suspend fun addPetsOfUser(@Body body: List<PetEntity>)

    @GET("user/{userId}/historyRenting")
    suspend fun getHistoryRenting(@Path("userId") userId: Int): List<HistoryRentingResponseEntity>

    @GET("user/hotels")
    suspend fun getAllHotels(): List<HotelEntity>

    @GET("user/hotels/{hotelId}/rooms")
    suspend fun getRoomsByHotel(@Path("hotelId") hotelId: Int): List<RoomEntity>

    @POST("user/addNewRenting")
    suspend fun addNewRentingForUser(@Body body: List<ScheduleEntity>)

    @POST("user/hotels/{hotelId}/freeRooms")
    suspend fun getAllFreeRoomByPeriod(@Path("hotelId") hotelId: Int, @Body dateRequest: DateRequestEntity): List<RoomEntity>

    @POST("user/{userId}/feedback")
    suspend fun sendMessageToAdmin(@Path("userId") userId: Int, @Body body: String)

    @POST("user/rent/autogenerateSchedule")
    suspend fun autogenerateSchedule(@Body body: AutogenerateRequestEntity): List<ScheduleFeedResponseEntity>

    @GET("user/{roomId}")
    suspend fun getRoomById(@Path("roomId") roomId: Int): List<RoomEntity>

    @GET("user/rent/schedule/{rentId}")
    suspend fun getScheduleFeed(@Path("rentId") rentId: Int): List<ScheduleFeedResponseEntity>

    @POST("user/rent/updateSchedule")
    suspend fun updateTimeOfScheduleFeed(@Body body: List<ScheduleFeedResponseEntity>)
}