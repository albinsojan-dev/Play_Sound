package com.albin.playSound.home

import android.app.Activity.MODE_PRIVATE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albin.playSound.R
import com.albin.playSound.bridge.SwitchClock
import com.albin.playSound.bridge.SwitchWifi
import java.time.Clock


@RequiresApi(Build.VERSION_CODES.O)
class SettingButton {

    @Composable
    fun SwitchWithCallback() {

        val context = LocalContext.current
        var isClock by remember { mutableStateOf(false) }
        var isSound by remember { mutableStateOf(false) }
        var isHourFormat by remember { mutableStateOf(false) }

        // Retrieve shared preferences
        val sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Read initial values from shared preferences
        isClock = sharedPreferences.getBoolean("play_Clock", false)
        isSound = sharedPreferences.getBoolean("play_Wifi", false)
        isHourFormat = sharedPreferences.getBoolean("24_hour_format", false)

        SwitchClock().Clock(context,isClock, isHourFormat)
        SwitchWifi().Wifi(context, isSound)

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.clock_sound),
                    fontSize = 25.sp,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isClock, onCheckedChange = { checked ->
                        isClock = checked
                        sharedPreferences.edit().putBoolean("play_Clock", checked).apply()
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.wifi_sound),
                    fontSize = 25.sp,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isSound, onCheckedChange = { checked ->
                        isSound = checked
                        sharedPreferences.edit().putBoolean("play_Wifi", checked).apply()
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.use_24_hour_format),
                    fontSize = 25.sp,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = isHourFormat, onCheckedChange = { checked ->
                        isHourFormat = checked
                        sharedPreferences.edit().putBoolean("24_hour_format", checked).apply()
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }
            if (isHourFormat) {
                Text(
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.twenty_four_time),
                )
            } else {
                Text(
                    text = stringResource(R.string.twelve_time)

                )
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun Show() {
        SwitchWithCallback()
    }
}
