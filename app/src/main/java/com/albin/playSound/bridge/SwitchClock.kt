package com.albin.playSound.bridge

import android.annotation.SuppressLint
import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.albin.playSound.clock.ClockTime

//import com.albin.playSound.clock.ClockTime


//import com.albin.playSound.clock.ClockTime

class SwitchClock {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun Clock(context: Context, isClock: Boolean) {
        if (isClock) {
            StartClock(context)
        } else {
            stopClock(context)
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
private fun StartClock(context: Context) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("play_Clock", true)
    editor.apply()
    ClockTime().displayTime(context)
    println("Function is running")
}

private fun stopClock(context: Context) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("play_Clock", false)
    editor.apply()
    println("Function has stopped")
}




