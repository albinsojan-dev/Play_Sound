package com.albin.playSound.bridge

import android.annotation.SuppressLint
import android.app.Activity.MODE_PRIVATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.albin.playSound.clock.Time

class SwitchClock {
    // Check if the Clock_button is on or off
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
// Start the function
private fun StartClock() {
    val context = LocalContext.current
    // Retrieve shared preferences
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("play_Clock", true)
    editor.apply()
    // Call the function to check if it's the first launch
    Time().checkAndRunIfFirstLaunch(context)
    println("Function is running")
}

@Composable
// Stop the function
private fun StopClock() {
    val context = LocalContext.current
    // Retrieve shared preferences
    val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("play_Clock", false)
    editor.apply()
    println("Function has stopped")
}