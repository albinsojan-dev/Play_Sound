package com.albin.playSound.main

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.albin.playSound.R

class RunningService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("InflateParams")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        start()
        return START_STICKY  // Ensures service is restarted if it gets terminated
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

