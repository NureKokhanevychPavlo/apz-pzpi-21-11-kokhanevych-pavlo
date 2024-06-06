package com.animal.hotel.data.utils

import java.time.LocalDateTime

class ValidationFood {

    companion object {

        fun isDateOfFeedingCorrect(date: LocalDateTime): Boolean {
            return date.isAfter(LocalDateTime.now())
        }
    }
}