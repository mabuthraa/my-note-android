package com.apipas.mynote.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi


object NetworkUtil {

    fun verifyAvailableNetwork(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isConnected(cm)
        } else {
            isConnectedLegacy(cm)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isConnected(cm: ConnectivityManager): Boolean {
        cm.run {
            val networkInfo = getNetworkCapabilities(cm.activeNetwork)

            return networkInfo?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            } ?: run {
                false
            }
        }
    }
}

@Suppress("DEPRECATION")
private fun isConnectedLegacy(cm: ConnectivityManager): Boolean {
    return cm.activeNetworkInfo?.isConnected ?: false
}

