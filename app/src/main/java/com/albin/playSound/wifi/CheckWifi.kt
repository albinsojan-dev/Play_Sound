package com.albin.playSound.wifi

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import com.albin.playSound.R

class CheckWifi {
    companion object {
        private const val PREFS_NAME = "MyAppPreferences"
        private const val CHECK_WIFI_FIRST_LAUNCH = "WifiFirstLaunch"
    }

    fun checkAndRunIfFirstLaunch(context: Context) {
        Log.d("CheckWifi", "checkAndRunIfFirstLaunch called")
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean(CHECK_WIFI_FIRST_LAUNCH, true)

        if (isFirstLaunch) {
            // Invoke the wifi check function only on the first launch
            isWifiConnected(context)

            // Set the flag to false so that this block is not executed again
            with(sharedPreferences.edit()) {
                putBoolean(CHECK_WIFI_FIRST_LAUNCH, false)
                apply()
            }
        }
    }

     private fun  isWifiConnected(context: Context): Boolean {
        Log.d("CheckWifi", "isWifiConnected called")
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                PlaySound().playSound(context, R.raw.yourdeviceconnecttowifi)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                PlaySound().playSound(context, R.raw.yourdevicedisconnectedfromwifi)
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        connectivityManager.requestNetwork(networkRequest, networkCallback)

        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
}
