package com.albin.playSound.broadcastReceiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.albin.playSound.clock.Time
import com.albin.playSound.service.RunningService
import com.albin.playSound.wifi.CheckWifi

class MyBroadcastReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ApplySharedPref")
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            if (context != null) {
                // Start service based on Android version
                val serviceIntent = Intent(context, RunningService::class.java)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // For Android 8.0 (Oreo) and above, use startForegroundService
                    context.startForegroundService(serviceIntent)
                } else {
                    // For versions below Oreo, start the service normally
                    context.startService(serviceIntent)
                }

                // Clear shared preferences value
                val sharedPreferences =
                    context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.commit()

                // Call the checkWifi function
                CheckWifi().checkAndRunIfFirstLaunch(context)
               Time().checkAndRunIfFirstLaunch(context)
            }
        }
    }

    // Define constants for shared preferences
    private val prefsName = CheckWifi.PREFS_NAME

}

