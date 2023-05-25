package com.example.fixturesapplication.utils

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUtils @Inject constructor() {

    fun getDate(pattern: String, date: String): Date? =
        SimpleDateFormat(pattern, Locale.US).apply {
            timeZone = TimeZone.getTimeZone("Africa/Cairo")
        }.parse(date)

    fun formatDate(pattern: String, date: Date): CharSequence? = DateFormat.format(pattern, date)

    companion object {
        const val Pattern_T_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val PATTERN_YYYY_MM_DD = "yyyy-MM-dd"
        const val PATTERN_HH_MM = "HH:mm"

    }
}