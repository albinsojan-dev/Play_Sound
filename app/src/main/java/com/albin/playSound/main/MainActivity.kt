package com.albin.playSound.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.albin.playSound.home.MainUi

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.FOREGROUND_SERVICE
                    ),
                    0
                )
            setContent {
                MainUi().NavController()
            }
            val intent = Intent(this, RunningService::class.java)
            startForegroundService(intent)
        }
    }
}
