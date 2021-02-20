package com.ltman.calendarextapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ltman.calendarext.CalendarExt
import com.ltman.calendarext.CalendarExtConfig
import com.ltman.calendarext.todayString
import com.ltman.calendarext.todayWithTimeString
import com.ltman.calendarextapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    class ViewData(
        context: Context,
        val now: Calendar
    ) {
        val today = now.todayString(context)
        val todayWithTime = now.todayWithTimeString(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        CalendarExt.configure(CalendarExtConfig(
            months_short_array = R.array.months_short_array,
            date_format__today = R.string.date_format__today,
            date_format__tomorrow = R.string.date_format__tomorrow,
            date_format__yesterday = R.string.date_format__yesterday,
            date_format__hour = R.string.date_format__hour,
            date_format__minute = R.string.date_format__minute,
            date_format__now = R.string.date_format__now,
            date_format__one_hour = R.string.date_format__one_hour,
            date_format__one_minute = R.string.date_format__one_minute,
            date_format__past = R.string.date_format__past,
            date_format__past_with_hyphen = R.string.date_format__past_with_hyphen,
            date_format__today_with_time = R.string.date_format__today_with_time,
            date_format__tomorrow_with_time = R.string.date_format__tomorrow_with_time,
            date_format__yesterday_with_time = R.string.date_format__yesterday_with_time
        ))

        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        binding.viewData = ViewData(this, Calendar.getInstance())

        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this

    }
}