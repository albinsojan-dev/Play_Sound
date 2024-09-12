package com.albin.playSound.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.albin.playSound.R

class RunningService : Service() {

    // This method is called when the service is bound to an activity
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("InflateParams")
    // This method is called when the service is started by an activity or other components
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        start()
        return START_STICKY  // Ensures service is restarted if it gets terminated
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // This method is called to start the service in the foreground with a notification to the user
    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Time")
            .setContentText("Hour")
            .setPriority(NotificationCompat.PRIORITY_HIGH)  // Set priority to match the channel's importance
            .build()
        startForeground(1, notification)
    }
}

