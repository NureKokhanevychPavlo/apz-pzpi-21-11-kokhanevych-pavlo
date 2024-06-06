package com.animal.hotel.domain.useCases.userCases

import com.animal.hotel.domain.sources.UserSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val userSource: UserSource
) {
    suspend operator fun invoke(userId: Int, message: String) {
        withContext(Dispatchers.IO) {
            userSource.sendMessageToAdmin(userId, message)
        }
    }
}