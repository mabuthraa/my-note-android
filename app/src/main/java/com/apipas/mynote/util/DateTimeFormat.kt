package com.apipas.mynote.util

import java.text.SimpleDateFormat
import java.util.*


object DateTimeFormat {
    const val DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss"

    fun formatTimeDate(date: Date, pattern: String = DEFAULT_DATE_TIME_FORMAT): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }
}