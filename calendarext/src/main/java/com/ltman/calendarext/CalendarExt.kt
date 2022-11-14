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
)

object CalendarExt {

    var currentTime: () -> Calendar = Calendar::getInstance
    internal lateinit var config: CalendarExtConfig

    fun configure(config: CalendarExtConfig) {
        this.config = config
    }
}

/**
 * use TodayPattern as date time pattern for convert date to string
 *
 * @return date string
*/
fun Calendar.toTodayString(context: Context): String {
    return this.toTodayString(context, CalendarExt.currentTime())
}

internal fun Calendar.toTodayString(context: Context, currentTime: Calendar): String {
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

/**
 * use TodayWithTimePattern as date time pattern for convert date to string
 *
 * @return date string
 */
fun Calendar.toTodayWithTimeString(context: Context): String {
    return this.toTodayWithTimeString(context, CalendarExt.currentTime())
}

internal fun Calendar.toTodayWithTimeString(context: Context, currentTime: Calendar): String {
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

/**
 * use TimeAgoPattern as date time pattern for convert date to string
 *
 * @return date string
 */
fun Calendar.toTimeAgoString(context: Context): String {
    return this.toTimeAgoString(context, CalendarExt.currentTime())
}

internal fun Calendar.toTimeAgoString(context: Context, currentTime: Calendar): String {
    val interval = (currentTime.timeInMillis - this.timeInMillis) / 1000

    val minute = 60
    val hour = 60 * minute

    val minAgo = interval / minute
    val hourAgo = interval / hour

    return when {
        minAgo < 0 -> {
            context.getString(CalendarExt.config.date_format__now)
        }

        this == currentTime || minAgo < 1 -> {
            context.getString(CalendarExt.config.date_format__now)
        }

        minAgo < 60 -> {
            if (minAgo == 1L)
                context.getString(CalendarExt.config.date_format__one_minute)
            else
                context.getString(CalendarExt.config.date_format__minute, minAgo)
        }

        hourAgo < 11 -> {
            if (hourAgo == 1L)
                context.getString(CalendarExt.config.date_format__one_hour)
            else
                context.getString(CalendarExt.config.date_format__hour, hourAgo)
        }

        else -> {
            this.toTodayWithTimeString(context, currentTime)
        }
    }
}


/**
 * use TimeAgoShortPattern as date time pattern for convert date to string
 *
 * @return date string
 */
fun Calendar.toTimeAgoShortString(context: Context): String {
    return this.toTimeAgoShortString(context, CalendarExt.currentTime())
}

internal fun Calendar.toTimeAgoShortString(context: Context, currentTime: Calendar): String {
    val interval = (currentTime.timeInMillis - this.timeInMillis) / 1000

    val secondPerMinute = 60
    val minutePerHour = 60 * secondPerMinute
    val hourPerDay = 24 * minutePerHour
    val dayPerWeek = 7 * hourPerDay

    val minAgo = interval / secondPerMinute
    val hourAgo = interval / minutePerHour
    val dayAgo = interval / hourPerDay
    val weekAgo = interval / dayPerWeek

    return when {
        minAgo < 0 -> context.getString(CalendarExt.config.date_format__now)
        this == currentTime || minAgo < 1 -> context.getString(CalendarExt.config.date_format__now)
        minAgo < 60 -> "${minAgo}m"
        hourAgo < 24 -> "${hourAgo}h"
        dayAgo < 7 -> "${dayAgo}d"
        weekAgo <= 52 -> "${weekAgo}w"
        else -> {
            val year = currentTime.get(Calendar.YEAR) - get(Calendar.YEAR)

            val postDateThisYear = CalendarExt.currentTime().apply {
                time = currentTime.time
            }
            postDateThisYear.timeInMillis = timeInMillis
            postDateThisYear.set(currentTime.get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DATE))

            return if (currentTime >= postDateThisYear)
                "${year}y"
            else
                "${year - 1}y"
        }
    }
}

/**
 * use DatePattern as date time pattern for convert date to string
 *
 * @return date string
 */
fun Calendar.toDateString(context: Context): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    val pattern = "d ___ yyyy"

    val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
    return convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])
}

/**
 * use DateWithTimePattern as date time pattern for convert date to string
 *
 * @param alwaysDisplayYear to force show year in date time pattern
 * @return date and time string
 */
fun Calendar.toDateWithTimeString(context: Context, alwaysDisplayYear: Boolean = false): String {
    return this.toDateWithTimeString(context, alwaysDisplayYear, CalendarExt.currentTime())
}

internal fun Calendar.toDateWithTimeString(context: Context, isForceShowYear: Boolean, currentTime: Calendar): String {
    val monthsShort = context.resources.getStringArray(CalendarExt.config.months_short_array)
    val timeStr = SimpleDateFormat("H:mm", Locale.getDefault()).format(time)
    val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR) && !isForceShowYear)
        "d ___"
    else "d ___ yyyy"

    val convertedDate = SimpleDateFormat(pattern, Locale.getDefault()).format(this.time)
    val date = convertedDate.replace("___", monthsShort[this.get(Calendar.MONTH)])

    return context.getString(CalendarExt.config.date_format__past, date, timeStr)
}

/**
 * Compare range between day of source calendar with today.
 *
 * @return difference day count (this function will return minus number if specific date after today)
 */
fun Calendar.dayAgo(): Int {
    return this.dayAgo(CalendarExt.currentTime())
}

internal fun Calendar.dayAgo(currentTime: Calendar): Int {
    val date1 = CalendarExt.currentTime()
    date1.time = this.time
    date1.set(Calendar.HOUR_OF_DAY, 0)
    date1.set(Calendar.MINUTE, 0)
    date1.set(Calendar.SECOND, 0)
    date1.set(Calendar.MILLISECOND, 0)

    val date2 = CalendarExt.currentTime()
    date2.time = currentTime.time
    date2.set(Calendar.HOUR_OF_DAY, 0)
    date2.set(Calendar.MINUTE, 0)
    date2.set(Calendar.SECOND, 0)
    date2.set(Calendar.MILLISECOND, 0)

    val diff = date2.timeInMillis - date1.timeInMillis
    return (diff / (24 * 60 * 60 * 1000)).toInt()
}

/**
 * use MonthPattern as date time pattern for convert date to string
 *
 * @return date string
 */
fun Calendar.toMonthString(): String {
    return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(time)
}