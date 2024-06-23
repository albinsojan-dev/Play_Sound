package com.albin.playsoundtheinonehourintervals.CheckWifiisConnectedOrNot

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.albin.playsoundtheinonehourintervals.R

class CheckWifi {
    fun isWifiConnected(context: Context): Boolean {
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