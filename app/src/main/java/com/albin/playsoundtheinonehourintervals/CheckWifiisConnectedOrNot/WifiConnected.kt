package com.albin.playsoundtheinonehourintervals.CheckWifiisConnectedOrNot

import android.content.Context
import com.albin.playsoundtheinonehourintervals.R

class WifiConnected {

    fun WhetherWifiConnected(context: Context) {

        val checkWifi = CheckWifi().isWifiConnected(context)

        when (checkWifi) {
            true -> PlaySound().playSound(context, R.raw.yourdeviceconnecttowifi)
            false -> PlaySound().playSound(context, R.raw.yourdevicedisconnectedfromwifi)
        }
    }
}
