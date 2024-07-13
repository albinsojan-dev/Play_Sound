package com.albin.playSound.bridge

import android.annotation.SuppressLint
import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.albin.playSound.clock.ClockTime

class SwitchClock {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun Clock(context: Context, isClock: Boolean, isHourFormat: Boolean) {
        if (isClock) {
            StartClock(context, isHourFormat)
        } else {
            stopClock(context)
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
private fun StartClock(context: Context, isHourFormat: Boolean) {
   ClockTime().startTimer(context,isHourFormat)
    println("Function is running")
}

private fun stopClock(context: Context) {
    // Implement stopping logic if necessary
    println("Function has stopped")
}




