package com.animal.hotel.data.authentication.source

import android.util.Log
import com.animal.hotel.data.ExceptionHandler
import com.animal.hotel.data.authentication.api.AuthApi
import com.animal.hotel.data.authentication.entities.RefreshRequestEntity
import com.animal.hotel.data.authentication.entities.SignInRequestEntity
import com.animal.hotel.data.user.api.UserApi
import com.animal.hotel.data.utils.LocalDateConverter
import com.animal.hotel.domain.models.AppSettings
import com.animal.hotel.domain.models.authentication.AuthenticationResponse
import com.animal.hotel.domain.models.authentication.RefreshRequest
import com.animal.hotel.domain.models.authentication.SignInRequest
import com.animal.hotel.domain.models.user.User
import com.animal.hotel.domain.sources.AuthenticationSource
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RetrofitAuthenticationSource @Inject constructor(
    private val authApi: AuthApi,
    private val userApi: UserApi,
    private val appSettings: AppSettings
) : ExceptionHandler(), AuthenticationSource {

    override suspend fun signIn(signInRequest: SignInRequest): AuthenticationResponse =
        wrapRetrofitExceptions {
            val authenticationResponse =
                authApi.signIn(SignInRequestEntity.toSignInRequestEntity(signInRequest))
                    .toAuthenticationResponse()
            appSettings.setCurrentToken(authenticationResponse.token)
            appSettings.setEmail(signInRequest.email)
            authenticationResponse
        }

    override suspend fun getPresentUser(email: String): Int =  wrapRetrofitExceptions {
          userApi.getPresentUser(email).userId
    }

    override suspend fun refreshToken(refreshRequest: RefreshRequest): AuthenticationResponse =
        wrapRetrofitExceptions {
            authApi.refreshToken(RefreshRequestEntity.toRefreshRequestEntity(refreshRequest))
                .toAuthenticationResponse()
        }

    override suspend fun registerUser(userRequest: User) = wrapRetrofitExceptions {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateConverter())
            .create()

        val userJson = gson.toJson(userRequest)
        val userRequestBody = RequestBody.create(MediaType.parse(JSON_TYPE_MEDIA), userJson)
        val body = MultipartBody.Part.createFormData(USER, null, userRequestBody)

        authApi.registerUser(body, createMultipartFromUri(userRequest.photoLink ?: ""))
    }

    private fun createMultipartFromUri(selectedImageUri: String): MultipartBody.Part? {
        val file = File(selectedImageUri)
        if (!file.exists()) {
            Log.e("File Error", "File does not exist: $selectedImageUri")
            return null
        }
        val requestFile = RequestBody.create(MultipartBody.FORM, file)
        val bodyTest = MultipartBody.Part.createFormData(PHOTO, file.name, requestFile)

        return bodyTest
    }

    companion object {
        const val USER = "user"
        const val PHOTO = "photo"
        const val JSON_TYPE_MEDIA = "application/json"
    }
}
