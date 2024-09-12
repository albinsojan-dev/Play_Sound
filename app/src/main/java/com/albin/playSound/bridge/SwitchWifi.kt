package com.albin.playSound.bridge

import android.annotation.SuppressLint
import android.app.Activity.MODE_PRIVATE
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.albin.playSound.wifi.CheckWifi
import kotlinx.coroutines.launch

class SwitchWifi {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    @Composable
    // check if wifi_button is on or off
    fun Wifi(context: Context, isSound: Boolean) {
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(isSound) {
            if (isSound) {
                // Start the function
                coroutineScope.launch {
                    startWifi(context)
                }
            } else {
                // Stop the function
                coroutineScope.launch {
                    stopWifi(context)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // start the function
    fun startWifi(context: Context) {
        // Retrieve shared preferences
        val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("play_wifi", true)
        editor.apply()
        // Call the function to check if it's the first launch
        CheckWifi().checkAndRunIfFirstLaunch(context)
        println("Function is running")
    }

    // stop the function
    private fun stopWifi(context: Context) {
        // Retrieve shared preferences
        val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("play_wifi", false)
        editor.apply()
        // Implement stopping logic if necessary
        println("Function has stopped")
    }
}
