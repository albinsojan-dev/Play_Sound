package com.albin.playSound.bridge

import android.annotation.SuppressLint
import android.app.Activity.MODE_PRIVATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.albin.playSound.clock.ClockViewModel

class SwitchClock {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun Clock(isClock: Boolean) {
        if (isClock) {
            StartClock()
        } else {
            StopClock()
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
private fun StartClock() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("play_Clock", true)
    editor.apply()
    ClockViewModel().checkAndRunIfFirstLaunch(context)
    println("Function is running")
}

@Composable
private fun StopClock() {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("play_Clock", false)
    editor.apply()
    println("Function has stopped")
}




