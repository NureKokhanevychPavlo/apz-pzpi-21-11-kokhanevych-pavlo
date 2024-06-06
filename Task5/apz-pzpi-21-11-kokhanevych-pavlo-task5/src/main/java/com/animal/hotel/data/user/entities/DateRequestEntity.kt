package com.animal.hotel.data.user.entities

import com.animal.hotel.domain.models.user.DateRequest
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class DateRequestEntity(
     val beginDate: LocalDateTime,
     val endDate: LocalDateTime
){
    fun toDateRequest(): DateRequest {
        return  DateRequest(beginDate, endDate)
    }

    companion object {
        fun toDateRequestEntity(dateRequest: DateRequest) =  DateRequestEntity (
            dateRequest.beginDate,
            dateRequest.endDate
        )
    }
}
