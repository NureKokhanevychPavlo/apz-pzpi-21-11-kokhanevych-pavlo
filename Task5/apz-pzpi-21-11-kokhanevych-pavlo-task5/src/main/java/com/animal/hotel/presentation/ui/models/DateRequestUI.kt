package com.animal.hotel.presentation.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class DateRequestUI(
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
): Parcelable 