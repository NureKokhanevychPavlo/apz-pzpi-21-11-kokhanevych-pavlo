package com.animal.hotel.domain.useCases.deviceCases

import com.animal.hotel.domain.sources.DeviceSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

class StartVideoStreamUseCase @Inject constructor(
    private val deviceSource: DeviceSource
) {
    suspend operator fun invoke(roomId: Int): ResponseBody {
        return withContext(Dispatchers.IO) {
            deviceSource.startVideoStream(roomId)
        }
    }
}