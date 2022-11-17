package com.kd.example.weather.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT = "EEE dd MMM"
    const val HOUR_FORMAT = "HH"

    fun changeDateFormatString(dateString:String, oldFormat:String, newFormat:String, locale: Locale)
    = SimpleDateFormat(oldFormat, locale)
        .parse(dateString)
        ?.let { SimpleDateFormat(newFormat, locale).format(it) }

    fun changeDateFormat(time:Long, newFormat:String) = SimpleDateFormat(newFormat,  Locale.UK).apply {
    }.format(Date(time* 1000L))

    fun changeDateFormatString3(time:Long) = SimpleDateFormat(
        DEFAULT_DATE_FORMAT, Locale.UK).format(Date(time* 1000L)).let{
        val utcDateFormat = SimpleDateFormat(DATE_FORMAT,  Locale.UK)
        utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        utcDateFormat.format(Date(time* 1000L))

    }

    fun getDateFormatToHour(dateString:String) = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.UK)
        .parse(dateString).let {
            SimpleDateFormat(HOUR_FORMAT, Locale.UK).format(it).toInt()
        }
}