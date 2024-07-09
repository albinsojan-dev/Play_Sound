package com.albin.playSound.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albin.playSound.R
import com.albin.playSound.bridge.SwitchClock
import com.albin.playSound.bridge.SwitchWifi

class SettingButton {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun SwitchWithCallback() {

        val context = LocalContext.current
        var isClock by remember { mutableStateOf(false) }
        var isSound by remember { mutableStateOf(false) }

        SwitchClock().Clock(context, isClock)
        SwitchWifi().Wifi(context, isSound)

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.End,
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
                    checked = isClock,
                    onCheckedChange = {
                        isClock = it
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White, // Color of the thumb when checked
                        uncheckedThumbColor = Color.White, // Color of the thumb when unchecked
                        checkedTrackColor = Color.Gray, // Color of the track when checked
                        uncheckedTrackColor = Color.LightGray // Color of the track when unchecked
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
                    checked = isSound,
                    onCheckedChange = {
                        isSound = it
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White, // Color of the thumb when checked
                        uncheckedThumbColor = Color.White, // Color of the thumb when unchecked
                        checkedTrackColor = Color.Gray, // Color of the track when checked
                        uncheckedTrackColor = Color.LightGray // Color of the track when unchecked
                    )
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
