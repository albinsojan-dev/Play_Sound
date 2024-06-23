package com.albin.playsoundtheinonehourintervals.PlaytheSoundinOneHourIntervals

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.text.format.DateFormat
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.albin.playsoundtheinonehourintervals.UserInterface.MainUi
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Locale

class SystemTime {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ClockComposable() {
        val now = remember { mutableStateOf(Calendar.getInstance().time) }
        val is24HourFormat = DateFormat.is24HourFormat(LocalContext.current)
        val timeFormat = remember {
            SimpleDateFormat(if (is24HourFormat) "HH:mm:ss" else "hh:mm:ss a", Locale.getDefault())
        }
        LaunchedEffect(Unit) {
            while (true) {
                now.value = Calendar.getInstance().time
                delay(1000)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = timeFormat.format(now.value),
                modifier = Modifier.align(Alignment.Center),
                fontSize = 65.sp,
                color = Color.White
            )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun Show() {
        ClockComposable()
    }
}
