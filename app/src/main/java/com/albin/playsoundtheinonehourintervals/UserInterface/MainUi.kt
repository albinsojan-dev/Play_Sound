package com.albin.playsoundtheinonehourintervals.UserInterface

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albin.playsoundtheinonehourintervals.R
import kotlinx.coroutines.delay
import java.util.Calendar
import java.util.Locale

class MainUi {

    @Composable
    fun ClockComposable() {
        val setting = remember { mutableStateOf(false) }
        val now = remember { mutableStateOf(Calendar.getInstance().time) }
        val is24HourFormat = android.text.format.DateFormat.is24HourFormat(LocalContext.current)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                ) {
                    Button(
                        onClick = { setting.value =! setting.value },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // Background color when the button is enabled
                            contentColor = Color.White, // Content (text) color when the button is enabled
                            disabledContainerColor = Color.Red, // Background color when the button is disabled
                            disabledContentColor = Color.Red // Content (text) color when the button is disabled
                        ),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_settings_24),
                            contentDescription = "Settings Icon",
                            tint = Color.Black // Color of the icon
                        )
//                        Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
//                        Text("Custom Button", color = Color.Black) // Text color
                    }
                }
            }
            Text(
                text = timeFormat.format(now.value),
                fontSize = 65.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            if (setting.value) {
                Setting().Switch()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun Show() {
        ClockComposable()
    }
}

