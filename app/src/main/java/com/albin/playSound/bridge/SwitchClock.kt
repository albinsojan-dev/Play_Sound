package com.albin.playSound.bridge

import android.annotation.SuppressLint
import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.albin.playSound.clock.SoundPlayer
import kotlinx.coroutines.launch

class SwitchClock {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun Clock(context: Context, isClock: Boolean) {
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(isClock) {
            if (isClock) {
                // Start the function
                coroutineScope.launch {
                    startClock(context)
                }
            } else {
                // Stop the function
                coroutineScope.launch {
                    stopClock(context)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startClock(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("play_Clock", true)
        editor.apply()
        SoundPlayer(context).startTimer()
        println("Function is running")
    }

    private fun stopClock(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("play_Clock", false)
        editor.apply()
        // Implement stopping logic if necessary
        println("Function has stopped")
    }
}
