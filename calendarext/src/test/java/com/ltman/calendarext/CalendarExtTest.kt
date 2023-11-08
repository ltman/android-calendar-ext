package com.ltman.calendarext

import android.content.Context
import android.content.res.Resources
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.*

class CalendarExtTest {

    private val context = mock(Context::class.java)
    private val resources = mock(Resources::class.java)

    companion object {
        private val date_2017_12_02_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2017)
            this.set(Calendar.DAY_OF_MONTH, 2)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2017_12_01_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2017)
            this.set(Calendar.DAY_OF_MONTH, 1)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_01_01_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 1)
            this.set(Calendar.MONTH, 0)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }
        private val date_2019_11_30_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 30)
            this.set(Calendar.MONTH, 10)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }
        private val date_2019_12_01_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 1)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_29_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 29)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_30_00H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 30)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_30_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 30)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_00H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2020_01_01_00H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2020)
            this.set(Calendar.DAY_OF_MONTH, 1)
            this.set(Calendar.MONTH, 0)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2020_01_01_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2020)
            this.set(Calendar.DAY_OF_MONTH, 1)
            this.set(Calendar.MONTH, 0)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2020_01_02_00H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2020)
            this.set(Calendar.DAY_OF_MONTH, 2)
            this.set(Calendar.MONTH, 0)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_23H_59M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_23H_58M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 58)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_23H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_22H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 22)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_13H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 13)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_31_12H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 12)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_25_00H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 25)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_12_24_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 24)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2019_01_01_00H_00M_00S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2019)
            this.set(Calendar.DAY_OF_MONTH, 1)
            this.set(Calendar.MONTH, 0)
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
        }

        private val date_2018_12_25_23H_59M_59S = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 2018)
            this.set(Calendar.DAY_OF_MONTH, 25)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.HOUR_OF_DAY, 23)
            this.set(Calendar.MINUTE, 59)
            this.set(Calendar.SECOND, 59)
            this.set(Calendar.MILLISECOND, 0)
        }
    }

    class R {
        object string {
            val date_format__now = 1
            val date_format__today = 2
            val date_format__tomorrow = 3
            val date_format__yesterday = 4

            val date_format__past = 5
            val date_format__yesterday_with_time = 6
            val date_format__today_with_time = 7
            val date_format__tomorrow_with_time = 8

            val date_format__one_minute = 9
            val date_format__minute = 10
            val date_format__one_hour = 11
            val date_format__hour = 12
        }
    }

    @Before
    fun prepare() {
        CalendarExt.configure(CalendarExtConfig(
            date_format__today = R.string.date_format__today,
            date_format__tomorrow = R.string.date_format__tomorrow,
            date_format__yesterday = R.string.date_format__yesterday,
            date_format__hour = R.string.date_format__hour,
            date_format__minute = R.string.date_format__minute,
            date_format__now = R.string.date_format__now,
            date_format__one_hour = R.string.date_format__one_hour,
            date_format__one_minute = R.string.date_format__one_minute,
            date_format__past = R.string.date_format__past,
            date_format__today_with_time = R.string.date_format__today_with_time,
            date_format__tomorrow_with_time = R.string.date_format__tomorrow_with_time,
            date_format__yesterday_with_time = R.string.date_format__yesterday_with_time
        ))

        `when`(context.getString(R.string.date_format__now)).thenReturn("Just now")
        `when`(context.getString(R.string.date_format__today)).thenReturn("Today")
        `when`(context.getString(R.string.date_format__tomorrow)).thenReturn("Tomorrow")
        `when`(context.getString(R.string.date_format__yesterday)).thenReturn("Yesterday")


        `when`(context.getString(R.string.date_format__past, "29 Dec", "23:59")).thenReturn("29 Dec at 23:59")
        `when`(context.getString(R.string.date_format__yesterday_with_time, "0:00")).thenReturn("Yesterday at 0:00")
        `when`(context.getString(R.string.date_format__yesterday_with_time, "23:59")).thenReturn("Yesterday at 23:59")
        `when`(context.getString(R.string.date_format__today_with_time, "0:00")).thenReturn("Today at 0:00")
        `when`(context.getString(R.string.date_format__today_with_time, "23:59")).thenReturn("Today at 23:59")
        `when`(context.getString(R.string.date_format__today_with_time, "12:59")).thenReturn("Today at 12:59")
        `when`(context.getString(R.string.date_format__tomorrow_with_time, "0:00")).thenReturn("Tomorrow at 0:00")
        `when`(context.getString(R.string.date_format__tomorrow_with_time, "23:59")).thenReturn("Tomorrow at 23:59")
        `when`(context.getString(R.string.date_format__past, "2 Jan 2020", "0:00")).thenReturn("2 Jan 2020 at 0:00")

        `when`(context.getString(R.string.date_format__one_minute)).thenReturn("1 minute ago")
        `when`(context.getString(R.string.date_format__minute, 59L)).thenReturn("59 minutes ago")
        `when`(context.getString(R.string.date_format__one_hour)).thenReturn("1 hour ago")
        `when`(context.getString(R.string.date_format__hour, 10L)).thenReturn("10 hours ago")

        `when`(context.getString(R.string.date_format__past, "31 Dec 2100", "8:50")).thenReturn("31 Dec 2100 at 8:50")

        `when`(context.resources).thenReturn(resources)
    }

    @Test
    fun dayAgo() {
        val calendar = Calendar.getInstance()
        Assert.assertEquals(0, calendar.dayAgo())
    }

    @Test
    fun `internal dayAgo`() {
        Assert.assertEquals(2, date_2019_12_29_23H_59M_59S.dayAgo(date_2019_12_31_23H_59M_59S))
        Assert.assertEquals(1, date_2019_12_30_00H_00M_00S.dayAgo(date_2019_12_31_23H_59M_59S))
        Assert.assertEquals(1, date_2019_12_30_23H_59M_59S.dayAgo(date_2019_12_31_23H_59M_59S))
        Assert.assertEquals(0, date_2019_12_31_00H_00M_00S.dayAgo(date_2019_12_31_23H_59M_59S))

        Assert.assertEquals(0, date_2019_12_31_23H_59M_59S.dayAgo(date_2019_12_31_23H_59M_59S))

        Assert.assertEquals(-1, date_2020_01_01_00H_00M_00S.dayAgo(date_2019_12_31_23H_59M_59S))
        Assert.assertEquals(-1, date_2020_01_01_23H_59M_59S.dayAgo(date_2019_12_31_23H_59M_59S))
        Assert.assertEquals(-2, date_2020_01_02_00H_00M_00S.dayAgo(date_2019_12_31_23H_59M_59S))
    }

    @Test
    fun todayString() {
        val calendar = Calendar.getInstance()
        `when`(context.getString(R.string.date_format__today)).thenReturn("Test Passed")
        `when`(context.getString(R.string.date_format__tomorrow)).thenReturn("Test Passed")
        Assert.assertEquals("Test Passed", calendar.toTodayString(context))
    }

    @Test
    fun `internal todayString`() {
        Assert.assertEquals(
            "29 Dec",
            date_2019_12_29_23H_59M_59S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Yesterday",
            date_2019_12_30_00H_00M_00S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Yesterday",
            date_2019_12_30_23H_59M_59S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Today",
            date_2019_12_31_00H_00M_00S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

        Assert.assertEquals(
            "Today",
            date_2019_12_31_23H_59M_59S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

        Assert.assertEquals(
            "Tomorrow",
            date_2020_01_01_00H_00M_00S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Tomorrow",
            date_2020_01_01_23H_59M_59S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "2 Jan 2020",
            date_2020_01_02_00H_00M_00S.toTodayString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

    }

    @Test
    fun todayWithTimeString() {
        val calendar = Calendar.getInstance()
        `when`(context.getString(ArgumentMatchers.eq(R.string.date_format__today_with_time), ArgumentMatchers.any())).thenReturn("Test Passed")
        `when`(context.getString(ArgumentMatchers.eq(R.string.date_format__tomorrow_with_time), ArgumentMatchers.any())).thenReturn("Test Passed")
        Assert.assertEquals("Test Passed", calendar.toTodayWithTimeString(context))
    }

    @Test
    fun `internal todayWithTimeString`() {
        Assert.assertEquals(
            "29 Dec at 23:59",
            date_2019_12_29_23H_59M_59S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Yesterday at 0:00",
            date_2019_12_30_00H_00M_00S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Yesterday at 23:59",
            date_2019_12_30_23H_59M_59S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Today at 0:00",
            date_2019_12_31_00H_00M_00S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

        Assert.assertEquals(
            "Today at 23:59",
            date_2019_12_31_23H_59M_59S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

        Assert.assertEquals(
            "Tomorrow at 0:00",
            date_2020_01_01_00H_00M_00S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Tomorrow at 23:59",
            date_2020_01_01_23H_59M_59S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "2 Jan 2020 at 0:00",
            date_2020_01_02_00H_00M_00S.toTodayWithTimeString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
    }


    @Test
    fun timeAgoWithTimeString() {
        val calendar = Calendar.getInstance()
        Assert.assertEquals("Just now", calendar.toTimeAgoString(context))
    }

    @Test
    fun `internal timeAgoWithTimeString`() {
        Assert.assertEquals(
            "29 Dec at 23:59",
            date_2019_12_29_23H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Yesterday at 0:00",
            date_2019_12_30_00H_00M_00S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Yesterday at 23:59",
            date_2019_12_30_23H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Today at 12:59",
            date_2019_12_31_12H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "10 hours ago",
            date_2019_12_31_13H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "1 hour ago",
            date_2019_12_31_22H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "59 minutes ago",
            date_2019_12_31_23H_00M_00S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "1 minute ago",
            date_2019_12_31_23H_58M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Just now",
            date_2019_12_31_23H_59M_00S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

        Assert.assertEquals(
            "Just now",
            date_2019_12_31_23H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )

        Assert.assertEquals(
            "Just now",
            date_2020_01_01_00H_00M_00S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Just now",
            date_2020_01_01_23H_59M_59S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
        Assert.assertEquals(
            "Just now",
            date_2020_01_02_00H_00M_00S.toTimeAgoString(context, date_2019_12_31_23H_59M_59S, null, locale = Locale.getDefault())
        )
    }

    @Test
    fun timeAgoShortString() {
        val calendar = Calendar.getInstance()
        Assert.assertEquals("Just now", calendar.toTimeAgoShortString(context))
    }

    @Test
    fun `internal timeAgoShortString`() {
        Assert.assertEquals(
            "1y",
            date_2017_12_02_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_01_23H_59M_59S)
        )
        Assert.assertEquals(
            "1y",
            date_2017_12_01_23H_59M_59S.toTimeAgoShortString(context, date_2019_01_01_23H_59M_59S)
        )
        Assert.assertEquals(
            "1y",
            date_2017_12_01_23H_59M_59S.toTimeAgoShortString(context, date_2019_11_30_23H_59M_59S)
        )
        Assert.assertEquals(
            "2y",
            date_2017_12_01_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_01_23H_59M_59S)
        )

        Assert.assertEquals(
            "1y",
            date_2018_12_25_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "52w",
            date_2019_01_01_00H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "1w",
            date_2019_12_24_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "6d",
            date_2019_12_25_00H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "2d",
            date_2019_12_29_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "1d",
            date_2019_12_30_00H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "1d",
            date_2019_12_30_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "23h",
            date_2019_12_31_00H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "1h",
            date_2019_12_31_22H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "59m",
            date_2019_12_31_23H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "1m",
            date_2019_12_31_23H_58M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "Just now",
            date_2019_12_31_23H_59M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )

        Assert.assertEquals(
            "Just now",
            date_2019_12_31_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )

        Assert.assertEquals(
            "Just now",
            date_2020_01_01_00H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "Just now",
            date_2020_01_01_23H_59M_59S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
        Assert.assertEquals(
            "Just now",
            date_2020_01_02_00H_00M_00S.toTimeAgoShortString(context, date_2019_12_31_23H_59M_59S)
        )
    }

    @Test
    fun dateString() {
        val notThisYearDate = Calendar.getInstance().apply {
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.YEAR, 2100)
            this.set(Calendar.HOUR_OF_DAY, 8)
            this.set(Calendar.MINUTE, 50)
        }

        Assert.assertEquals("31 Dec 2100", notThisYearDate.toDateString())
    }

    @Test
    fun dateStringWithOffset() {
        val notThisYearDate = Calendar.getInstance().apply {
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.YEAR, 2100)
            this.set(Calendar.HOUR_OF_DAY, 19)
            this.set(Calendar.MINUTE, 50)
            timeZone = TimeZone.getTimeZone("UTC")
        }

        Assert.assertEquals("1 Jan 2101", notThisYearDate.toDateString( timeZone = TimeZone.getTimeZone("GMT+7")))
    }

    @Test
    fun dateWithTimeString() {
        val calendar = Calendar.getInstance()
        `when`(context.getString(ArgumentMatchers.eq(R.string.date_format__past), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn("Test Passed")
        Assert.assertEquals("Test Passed", calendar.toDateWithTimeString(context))
    }

    @Test
    fun `internal dateWithTimeString`() {
        val notThisYearDate = Calendar.getInstance().apply {
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.YEAR, 2100)
            this.set(Calendar.HOUR_OF_DAY, 8)
            this.set(Calendar.MINUTE, 50)
        }

        Assert.assertEquals("31 Dec 2100 at 8:50", notThisYearDate.toDateWithTimeString(context))
    }

    @Test
    fun monthAndYearString() {
        val date = Calendar.getInstance().apply {
            this.set(Calendar.DAY_OF_MONTH, 31)
            this.set(Calendar.MONTH, 11)
            this.set(Calendar.YEAR, 2100)
            this.set(Calendar.HOUR_OF_DAY, 8)
            this.set(Calendar.MINUTE, 50)
        }

        Assert.assertEquals("December 2100", date.toMonthString())
    }

}