package com.albin.playSound.userInterface

import android.annotation.SuppressLint
import android.os.Build
import android.text.format.DateFormat
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albin.playSound.R
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainUi {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    // Navigation from first screen to Second screen
    fun NavController() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "first_screen") {
            composable("first_screen") {
                ClockComposable(navController = navController)
            }
            composable("second_screen") {
                // Second screen screen
                SecondScreen()
            }
        }
    }
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    // Second screen
    fun SecondScreen() {
        // Call the switch button
        SettingButton().SwitchWithCallback()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    // First screen with clock time and setting button
    fun ClockComposable(navController: NavController) {
        val context = LocalContext.current
        // Check if the device is in 24-hour format
        val isHourFormat = DateFormat.is24HourFormat(context)
        val timeFormatter = if (isHourFormat) {
            // Format time in 24-hour format
            DateTimeFormatter.ofPattern("HH:mm:ss")
        } else {
            // Format time in 12-hour format
            DateTimeFormatter.ofPattern("hh:mm:ss a")
        }

        // State to hold the current time
        val now = remember { mutableStateOf(Calendar.getInstance().time) }
        val currentTime = LocalTime.now()
        val formattedTime = currentTime.format(timeFormatter)

        LaunchedEffect(Unit) {
            // Update the time every second
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
                        // Navigate to second screen when setting button is clicked
                        onClick = { navController.navigate("second_screen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White, // Background color when the button is enabled
                            contentColor = Color.White, // Content (text) color when the button is enabled
                            disabledContainerColor = Color.Red, // Background color when the button is disabled
                            disabledContentColor = Color.Red // Content (text) color when the button is disabled
                        ),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(
                            // Setting button icon
                            painter = painterResource(id = R.drawable.baseline_settings_24),
                            contentDescription = "Settings Icon",
                            tint = Color.Black // Color of the icon
                        )
                    }
                }
            }
            Text(
                // Get the user current time and display it
                text = formattedTime.format(now.value),
                fontSize = 65.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    // Show the first screen preview
    fun Show() {
        NavController()
    }
}

