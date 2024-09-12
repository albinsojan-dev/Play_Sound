package com.albin.playSound.clock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import androidx.annotation.RequiresApi
import com.albin.playSound.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
// Check the time and play sound in every one hour gap
class Time {
    // Define constants for shared preferences
    // Check the application is fist launch
    companion object {
        private const val PREFS_NAME = "MyAppPreferences"
        private const val CLOCK_TIME_FIRST_LAUNCH = "ClockFirstLaunch"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    // Check if it's the first launch
    fun checkAndRunIfFirstLaunch(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean(CLOCK_TIME_FIRST_LAUNCH, true)

        if (isFirstLaunch) {
            // Invoke the wifi check function only on the first launch
            displayTime(context)

            // Set the flag to false so that this block is not executed again
            with(sharedPreferences.edit()) {
                putBoolean(CLOCK_TIME_FIRST_LAUNCH, false)
                apply()
            }
        }
    }
    // Get the current time
    private fun getCurrentTime(context: Context): String {
        // Get the current date and time
        val calendar = Calendar.getInstance()

        // Check if the user has set the device to 24-hour format
        val is24HourFormat = DateFormat.is24HourFormat(context)

        // Define the time format based on the user's preference
        val timeFormat = if (is24HourFormat) {
            // 24-hour format
            SimpleDateFormat("HH:mm", Locale.getDefault())
        } else {
            // 12-hour format
            SimpleDateFormat("hh:mm a", Locale.getDefault())
        }

        // Format the current time
        val formattedTime = timeFormat.format(calendar.time)
        Log.d("ClockTime", "Current time: $formattedTime")

        // Return the formatted time
        return formattedTime
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    // Play the sound in every one hour gap
    fun displayTime(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                val currentTime = getCurrentTime(context)
                when (currentTime) {

                    in "01:00 AM".."01:00 PM" , "01:00" -> {
                        PlaySound().playSound(context, R.raw.one)
                    }

                    in "02:00 AM".."02:00 PM", "02:00" -> {
                        PlaySound().playSound(context, R.raw.two)
                    }

                    in "03:00 AM".."03:00 PM" ,"03:00" -> {
                        PlaySound().playSound(context, R.raw.three)
                    }

                    in "04:00 AM".."04:00 PM", "04:00" -> {
                        PlaySound().playSound(context, R.raw.four)
                    }

                    in "05:00 AM".."05:00 PM", "05:00" -> {
                        PlaySound().playSound(context, R.raw.five)
                    }

                    in "06:00 AM".."06:00 PM", "06:00" -> {
                        PlaySound().playSound(context, R.raw.six)
                    }

                    in "07:00 AM".."07:00 PM", "07:00" -> {
                        PlaySound().playSound(context, R.raw.seven)
                    }

                    in "08:00 AM".."08:00 PM", "08:00" -> {
                        PlaySound().playSound(context, R.raw.eight)
                    }

                    in "09:00 AM".."09:00 PM", "09:00" -> {
                        PlaySound().playSound(context, R.raw.nine)
                    }

                    in "10:00 AM".."10:00 PM", "10:00" -> {
                        PlaySound().playSound(context, R.raw.ten)
                    }

                    in "11:00 AM".."11:00 PM", "11:00" -> {
                        PlaySound().playSound(context, R.raw.eleven)
                    }

                    in "12:00 AM".."12:00 PM", "12:00" -> {
                        PlaySound().playSound(context, R.raw.twelve)
                    }

                    in "13:00" -> {
                        PlaySound().playSound(context, R.raw.thirteen)
                    }

                    in "14:00" -> {
                        PlaySound().playSound(context, R.raw.fourteen)
                    }

                    in "15:00" -> {
                        PlaySound().playSound(context, R.raw.fifteen)
                    }

                    in "16:00" -> {
                        PlaySound().playSound(context, R.raw.sixteen)
                    }

                    in "17:00" -> {
                        PlaySound().playSound(context, R.raw.seventeen)
                    }

                    in "18:00" -> {
                        PlaySound().playSound(context, R.raw.eighteen)
                    }

                    in "19:00" -> {
                        PlaySound().playSound(context, R.raw.nineteen)
                    }

                    in "20:00" -> {
                        PlaySound().playSound(context, R.raw.twenty)
                    }

                    in "21:00" -> {
                        PlaySound().playSound(context, R.raw.twenty_one)
                    }

                    in "22:00" -> {
                        PlaySound().playSound(context, R.raw.twenty_two)
                    }

                    in "23:00" -> {
                        PlaySound().playSound(context, R.raw.twenty_three)
                    }
                    in "00:00" -> {
                        PlaySound().playSound(context, R.raw.zero)
                    }
                }
                delay(60000)
            }
        }
    }
}












