package com.kd.example.weather.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val DEFAULT_DATE_FORMAT = "yyyy-mm-dd HH:mm:ss"
    const val DATE_FORMAT = "EEE dd MMM"
    const val HOUR_FORMAT = "HH"


    fun changeDateFormatString(dateString:String, oldFormat:String, newFormat:String, locale: Locale)
    = SimpleDateFormat(oldFormat, locale)
        .parse(dateString)
        ?.let { SimpleDateFormat(newFormat, locale).format(it) }

    fun getDateFormatToHour(dateString:String) = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.UK)
        .parse(dateString).let {
            SimpleDateFormat(HOUR_FORMAT, Locale.UK).format(it).toInt()
        }

}