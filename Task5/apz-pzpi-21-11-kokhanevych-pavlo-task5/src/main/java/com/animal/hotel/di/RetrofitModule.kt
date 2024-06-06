package com.animal.hotel.di

import com.animal.hotel.data.authentication.api.AuthApi
import com.animal.hotel.data.device.api.DeviceApi
import com.animal.hotel.data.user.api.UserApi
import com.animal.hotel.data.utils.LocalDateConverter
import com.animal.hotel.data.utils.LocalDateTimeAdapter
import com.animal.hotel.domain.models.AppSettings
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    private const val API_URL: String = "https://902f-185-151-84-115.ngrok-free.app/"

    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(settings: AppSettings): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(createAuthorizationInterceptor(settings))
            .build()
    }

    private fun createAuthorizationInterceptor(settings: AppSettings): Interceptor {
        return Interceptor { chain ->
            val newBuilder = chain.request().newBuilder()
            val token = settings.getCurrentToken()
            if (token != null) {
                newBuilder.addHeader("Authorization", "Bearer $token")
            }
            return@Interceptor chain.proceed(newBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {

        val builder = GsonBuilder()
        builder.registerTypeAdapter(object : TypeToken<LocalDate?>() {}.type, LocalDateConverter())
        builder.registerTypeAdapter(object : TypeToken<LocalDateTime?>() {}.type, LocalDateTimeAdapter())
        val gson = builder.create()
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(API_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDeviceApi(
        retrofit: Retrofit
    ): DeviceApi {
        return retrofit.create(DeviceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}