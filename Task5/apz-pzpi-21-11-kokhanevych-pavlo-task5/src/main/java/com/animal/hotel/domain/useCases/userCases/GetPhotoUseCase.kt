package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val userSource: UserSource
) {

    suspend operator fun invoke(userId: Int): ByteArray? {
        return withContext(Dispatchers.IO) {
            userSource.getUserPhoto(userId)
        }
    }
}