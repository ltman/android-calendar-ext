package com.ltman.calendarext

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.*

data class CalendarExtConfig(
    @ArrayRes
    val months_short_array: Int,
    @StringRes
    val date_format__today: Int,
    @StringRes
    val date_format__yesterday: Int,
    @StringRes
    val date_format__tomorrow: Int,
    @StringRes
    val date_format__today_with_time: Int,
    @StringRes
    val date_format__yesterday_with_time: Int,
    @StringRes
    val date_format__tomorrow_with_time: Int,
    @StringRes
    val date_format__past: Int,
    @StringRes
    val date_format__now: Int,
    @StringRes
    val date_format__one_minute: Int,
    @StringRes
    val date_format__minute: Int,
    @StringRes
    val date_format__one_hour: Int,
    @StringRes
    val date_format__hour: Int,
    @StringRes
    val date_format__past_with_hyphen: Int
)

object CalendarExt {

    internal lateinit var config: CalendarExtConfig

    fun configure(config: CalendarExtConfig) {
        this.config = config
    }
}

fun Calendar.todayString(context: Context): String {
    return this.todayString(context, Calendar.getInstance())
}

internal fun Calendar.todayString(context: Context, currentTime: Calendar): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    return when (this.dayAgo(currentTime)) {
        0 -> context.getString(CalendarExt.config.date_format__today)
        1 -> context.getString(CalendarExt.config.date_format__yesterday)
        -1 -> context.getString(CalendarExt.config.date_format__tomorrow)
        else -> {
            val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR))
                "d ___"
            else "d ___ yyyy"

            val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
            convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])
        }
    }
}

fun Calendar.todayWithTimeString(context: Context): String {
    return this.todayWithTimeString(context, Calendar.getInstance())
}

internal fun Calendar.todayWithTimeString(context: Context, currentTime: Calendar): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    val timeStr = SimpleDateFormat("H:mm", Locale.getDefault()).format(time)
    return when (this.dayAgo(currentTime)) {
        0 -> context.getString(CalendarExt.config.date_format__today_with_time, timeStr)
        1 -> context.getString(CalendarExt.config.date_format__yesterday_with_time, timeStr)
        -1 -> context.getString(CalendarExt.config.date_format__tomorrow_with_time, timeStr)
        else -> {
            val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR))
                "d ___"
            else "d ___ yyyy"

            val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
            val date = convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])
            context.getString(CalendarExt.config.date_format__past, date, timeStr)
        }
    }
}

fun Calendar.timeAgoWithTimeString(context: Context): String {
    return this.timeAgoWithTimeString(context, Calendar.getInstance())
}

internal fun Calendar.timeAgoWithTimeString(context: Context, currentTime: Calendar): String {
    val interval = (currentTime.timeInMillis - this.timeInMillis) / 1000

    val minute = 60
    val hour = 60 * minute

    val minAgo = interval / minute
    val hourAgo = interval / hour

    return when {
        minAgo < 0 -> context.getString(CalendarExt.config.date_format__now)

        this == currentTime || minAgo < 1 -> context.getString(CalendarExt.config.date_format__now)

        minAgo < 60 -> {
            return if (minAgo == 1L) context.getString(CalendarExt.config.date_format__one_minute)
            else context.getString(CalendarExt.config.date_format__minute, minAgo)
        }

        hourAgo < 11 -> {
            return if (hourAgo == 1L) context.getString(CalendarExt.config.date_format__one_hour)
            else context.getString(CalendarExt.config.date_format__hour, hourAgo)
        }

        else -> this.todayWithTimeString(context, currentTime)
    }
}


fun Calendar.timeAgoShortString(context: Context): String {
    return this.timeAgoShortString(context, Calendar.getInstance())
}

internal fun Calendar.timeAgoShortString(context: Context, currentTime: Calendar): String {
    val interval = (currentTime.timeInMillis - this.timeInMillis) / 1000

    val minute = 60
    val hour = 60 * minute
    val day = 24 * hour
    val week = 7 * day

    val minAgo = interval / minute
    val hourAgo = interval / hour
    val dayAgo = interval / day
    val weekAgo = interval / week

    return when {
        minAgo < 0 -> context.getString(CalendarExt.config.date_format__now)
        this == currentTime || minAgo < 1 -> context.getString(CalendarExt.config.date_format__now)
        minAgo < 60 -> "${minAgo}m"
        hourAgo < 24 -> "${hourAgo}h"
        dayAgo < 7 -> "${interval / day}d"
        weekAgo <= 52 -> "${interval / week}w"
        else -> {
            val year = currentTime.get(Calendar.YEAR) - get(Calendar.YEAR)

            val postDateThisYear = Calendar.getInstance().apply {
                time = currentTime.time
            }
            postDateThisYear.timeInMillis = timeInMillis
            postDateThisYear.set(currentTime.get(Calendar.YEAR), get(Calendar.MONTH), currentTime.get(Calendar.DATE))

            return if (currentTime >= postDateThisYear)
                "${year}y"
            else
                "${year - 1}y"
        }
    }
}

fun Calendar.dateWithHyphenString(context: Context): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    val timeStr = SimpleDateFormat("H:mm", Locale.getDefault()).format(time)
    val pattern = "d ___ yyyy"

    val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
    val date = convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])
    return context.getString(CalendarExt.config.date_format__past_with_hyphen, date, timeStr)
}

fun Calendar.dateString(context: Context): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    val pattern = "d ___ yyyy"

    val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
    return convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])
}

fun Calendar.dateWithTimeString(context: Context): String {
    return this.dateWithTimeString(context, Calendar.getInstance())
}

internal fun Calendar.dateWithTimeString(context: Context, currentTime: Calendar): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    val timeStr = SimpleDateFormat("H:mm", Locale.getDefault()).format(time)
    val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR))
        "d ___"
    else "d ___ yyyy"

    val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
    val date = convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])

    return context.getString(CalendarExt.config.date_format__past, date, timeStr)
}

fun Calendar.dayAgo(): Int {
    return this.dayAgo(Calendar.getInstance())
}

internal fun Calendar.dayAgo(currentTime: Calendar): Int {
    val date1 = Calendar.getInstance()
    date1.time = this.time
    date1.set(Calendar.HOUR_OF_DAY, 0)
    date1.set(Calendar.MINUTE, 0)
    date1.set(Calendar.SECOND, 0)
    date1.set(Calendar.MILLISECOND, 0)

    val date2 = Calendar.getInstance()
    date2.time = currentTime.time
    date2.set(Calendar.HOUR_OF_DAY, 0)
    date2.set(Calendar.MINUTE, 0)
    date2.set(Calendar.SECOND, 0)
    date2.set(Calendar.MILLISECOND, 0)

    val diff = date2.timeInMillis - date1.timeInMillis
    return (diff / (24 * 60 * 60 * 1000)).toInt()
}

fun Calendar.monthAndYearString(): String {
    return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(time)
}