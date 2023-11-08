package com.ltman.calendarext

import android.content.Context
import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

data class CalendarExtConfig(
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
 * Convert Calendar to String
 *
 * Example
 *
 * Today | Yesterday | Tomorrow	| d MMM
 *
 * @return date string
 */
fun Calendar.toTodayString(
    context: Context,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {
    return this.toTodayString(context, CalendarExt.currentTime(), timeZone, locale)
}

internal fun Calendar.toTodayString(
    context: Context,
    currentTime: Calendar,
    timeZone: TimeZone?,
    locale: Locale
): String {
    return when (this.dayAgo(currentTime)) {
        0 -> context.getString(CalendarExt.config.date_format__today)
        1 -> context.getString(CalendarExt.config.date_format__yesterday)
        -1 -> context.getString(CalendarExt.config.date_format__tomorrow)
        else -> {

            val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR))
                "d MMM"
            else "d MMM yyyy"

            val dateFormat = SimpleDateFormat(pattern, locale)

            timeZone?.let {
                dateFormat.timeZone = timeZone
            }

            dateFormat.format(this.time)
        }
    }
}

/**
 * Convert Calendar to String
 *
 * Example
 *
 * Today at H:mm | Yesterday at H:mm | Tomorrow at H:mm	d | MMM at H:mm
 *
 * @return date string
 */
fun Calendar.toTodayWithTimeString(
    context: Context,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {
    return this.toTodayWithTimeString(context, CalendarExt.currentTime(), timeZone, locale)
}

internal fun Calendar.toTodayWithTimeString(
    context: Context,
    currentTime: Calendar,
    timeZone: TimeZone?,
    locale: Locale
): String {
    val timeFormat = SimpleDateFormat("H:mm", locale)

    timeZone?.let {
        timeFormat.timeZone = timeZone
    }

    val timeStr = timeFormat.format(time)

    return when (this.dayAgo(currentTime)) {
        0 -> context.getString(CalendarExt.config.date_format__today_with_time, timeStr)
        1 -> context.getString(CalendarExt.config.date_format__yesterday_with_time, timeStr)
        -1 -> context.getString(CalendarExt.config.date_format__tomorrow_with_time, timeStr)
        else -> {
            val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR))
                "d MMM"
            else "d MMM yyyy"

            val dateFormat = SimpleDateFormat(pattern, locale)

            timeZone?.let {
                dateFormat.timeZone = timeZone
            }

            val convertedDate = dateFormat.format(this.time)

            context.getString(CalendarExt.config.date_format__past, convertedDate, timeStr)
        }
    }
}

/**
 * Convert Calendar to String
 *
 * Example
 *
 * Just now | X minutes ago | X hours ago | Today at H:mm | Yesterday at H:mm | d MMM at H:mm
 *
 * @return date string
 */
fun Calendar.toTimeAgoString(
    context: Context,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {
    return this.toTimeAgoString(context, CalendarExt.currentTime(), timeZone, locale)
}

internal fun Calendar.toTimeAgoString(
    context: Context,
    currentTime: Calendar,
    timeZone: TimeZone?,
    locale: Locale = Locale.getDefault()
): String {
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
            this.toTodayWithTimeString(context, currentTime, timeZone, locale)
        }
    }
}


/**
 * Convert Calendar to String
 *
 * Example
 *
 * Just now | X m(minute) or h(hour) or d(day) or w(week) or y(year)
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
            postDateThisYear.set(
                currentTime.get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DATE)
            )

            return if (currentTime >= postDateThisYear)
                "${year}y"
            else
                "${year - 1}y"
        }
    }
}

/**
 * Convert Calendar to String
 *
 * Example
 *
 * d MMM yyyy
 *
 * @return date string
 */
fun Calendar.toDateString(
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {
    val pattern = "d MMM yyyy"

    val dateFormat = SimpleDateFormat(pattern, locale)

    timeZone?.let {
        dateFormat.timeZone = timeZone
    }
    return dateFormat.format(this.time)
}

/**
 * Convert Calendar to String
 *
 * Example
 *
 * d MMM at H:mm | d MMM yyyy at H:mm
 *
 * @param alwaysDisplayYear to force show year in date time pattern
 * @return date and time string
 */
fun Calendar.toDateWithTimeString(
    context: Context,
    alwaysDisplayYear: Boolean = false,
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {
    return this.toDateWithTimeString(
        context,
        alwaysDisplayYear,
        CalendarExt.currentTime(),
        timeZone,
        locale
    )
}

internal fun Calendar.toDateWithTimeString(
    context: Context,
    isForceShowYear: Boolean,
    currentTime: Calendar,
    timeZone: TimeZone? = null,
    locale: Locale
): String {
    val timeFormat = SimpleDateFormat("H:mm", locale)

    timeZone?.let {
        timeFormat.timeZone = timeZone
    }

    val timeStr = timeFormat.format(time)

    val pattern = if (currentTime.get(Calendar.YEAR) == this.get(Calendar.YEAR) && !isForceShowYear)
        "d MMM"
    else "d MMM yyyy"

    val dateFormat = SimpleDateFormat(pattern, locale)

    timeZone?.let {
        dateFormat.timeZone = timeZone
    }

    val convertedDate = dateFormat.format(this.time)

    return context.getString(CalendarExt.config.date_format__past, convertedDate, timeStr)
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
 * Convert Calendar to String
 *
 * Example
 *
 * MMMM yyyy
 *
 * @return date string
 */
fun Calendar.toMonthString(
    timeZone: TimeZone? = null,
    locale: Locale = Locale.getDefault()
): String {
    val dateFormat = SimpleDateFormat("MMMM yyyy", locale)

    timeZone?.let {
        dateFormat.timeZone = timeZone
    }

    return dateFormat.format(time)
}