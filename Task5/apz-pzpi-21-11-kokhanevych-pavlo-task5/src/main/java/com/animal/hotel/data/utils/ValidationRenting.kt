package com.animal.hotel.data.utils

import java.time.LocalDateTime

class ValidationRenting {

    companion object {
        fun isRentingDatesCorrect(beginDate: LocalDateTime?, endDate: LocalDateTime?): Boolean {
            if(beginDate == null || endDate == null) return false
            return beginDate.isBefore(endDate) && LocalDateTime.now().isBefore(beginDate)
        }
    }
}