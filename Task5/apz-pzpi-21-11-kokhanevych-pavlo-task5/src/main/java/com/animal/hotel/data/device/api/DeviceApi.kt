package com.animal.hotel.data.device.api

import com.animal.hotel.data.device.entities.StateRoomRequestEntity
import com.animal.hotel.data.device.entities.StateRoomResponseEntity
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DeviceApi {

    @GET("device/{roomId}/state")
    suspend fun getStateOfRoom(@Path("roomId") roomId: Int): StateRoomResponseEntity

    @POST("device/{roomId}/setState")
    suspend fun setNewStateOfRoom(@Path("roomId") roomId: Int, @Body body: StateRoomResponseEntity)

    @POST("device/{roomId}/updateState")
    suspend fun updateStateOfRoom(@Path("roomId") roomId: Int, @Body body: StateRoomRequestEntity)

    @GET("device/{roomId}/startStream")
    suspend fun startVideoStream(@Path("roomId") roomId: Int): ResponseBody
}