package com.albin.playsoundtheinonehourintervals

import android.os.Build
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
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalTime

class SystemTime {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun ClockComposable() {
        val now = remember { mutableStateOf(LocalTime.now()) }


        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center
        ) {
            Text(
                text = now.value.format(java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss".toString(),
               java.util.Locale.getDefault())), modifier = Modifier.align(Alignment.Center),
                fontSize = 65.sp,
                color = Color.White
            )
        }
        LaunchedEffect(Unit) {
            while (true) {
                now.value = LocalTime.now()
                delay(1000)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun Show() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ClockComposable()
        }

    }
}
