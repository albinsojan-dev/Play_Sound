package com.albin.playSound.clock

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.albin.playSound.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
class ClockTime {

    fun startTimer(context: Context, isHourFormat: Boolean) {
        if (isHourFormat) {
            start24HourTimer(context)
        } else {
            start12HourTimer(context)
        }
    }

    private fun start24HourTimer(context: Context) {
        var previousHour = -1
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val currentTime = LocalTime.now()
                val currentHour = currentTime.hour
                if (previousHour != currentHour) {
                    previousHour = currentHour
                    PlaySound().play(currentHour, twentyFourHourSoundResources, context)
                }
                delay(60_0) // Check every minute
            }
        }
    }

    private fun start12HourTimer(context: Context) {
        var previousHour = -1
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val currentTime = LocalTime.now()
                val currentHour = currentTime.hour % 12
                if (previousHour != currentHour) {
                    previousHour = currentHour
                    PlaySound().play(currentHour, twelveHourSoundResources, context)
                }
                delay(60_0) // Check every minute
            }
        }
    }

    private val twentyFourHourSoundResources = listOf(
        R.raw.zero, R.raw.one, R.raw.two, R.raw.three, R.raw.four,
        R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine,
        R.raw.ten, R.raw.eleven, R.raw.twelve, R.raw.thirteen, R.raw.fourteen,
        R.raw.fifteen, R.raw.sixteen, R.raw.seventeen, R.raw.eighteen,
        R.raw.nineteen, R.raw.twenty, R.raw.twenty_one, R.raw.twenty_two, R.raw.twenty_three
    )

    private val twelveHourSoundResources = listOf(
        R.raw.twelve, R.raw.one, R.raw.two, R.raw.three, R.raw.four,
        R.raw.five, R.raw.six, R.raw.seven, R.raw.eight, R.raw.nine,
        R.raw.ten, R.raw.eleven, R.raw.twelve
    )
}




