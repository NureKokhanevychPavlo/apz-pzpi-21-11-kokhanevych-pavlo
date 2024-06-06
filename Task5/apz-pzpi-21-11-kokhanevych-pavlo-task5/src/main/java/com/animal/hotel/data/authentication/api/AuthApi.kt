package com.animal.hotel.data.authentication.api

import com.animal.hotel.data.authentication.entities.AuthenticationResponseEntity
import com.animal.hotel.data.authentication.entities.RefreshRequestEntity
import com.animal.hotel.data.authentication.entities.SignInRequestEntity
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApi {

    @POST("authentication/signIn")
    suspend fun signIn(@Body body: SignInRequestEntity): AuthenticationResponseEntity

    @POST("authentication/refresh")
    suspend fun refreshToken(@Body body: RefreshRequestEntity): AuthenticationResponseEntity

    @Multipart
    @POST("authentication/register")
    suspend fun registerUser(@Part user: MultipartBody.Part, @Part upload: MultipartBody.Part?)
}