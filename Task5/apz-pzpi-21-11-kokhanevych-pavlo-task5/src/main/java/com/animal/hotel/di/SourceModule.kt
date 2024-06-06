package com.animal.hotel.di

import com.animal.hotel.data.authentication.source.RetrofitAuthenticationSource
import com.animal.hotel.data.device.source.RetrofitDeviceSource
import com.animal.hotel.data.user.source.RetrofitUserSource
import com.animal.hotel.domain.sources.AuthenticationSource
import com.animal.hotel.domain.sources.DeviceSource
import com.animal.hotel.domain.sources.UserSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindAuthSource(authSource: RetrofitAuthenticationSource): AuthenticationSource

    @Binds
    abstract fun bindUserSource(userSource: RetrofitUserSource): UserSource

    @Binds
    abstract fun bindDeviceSource(deviceSource: RetrofitDeviceSource): DeviceSource
}