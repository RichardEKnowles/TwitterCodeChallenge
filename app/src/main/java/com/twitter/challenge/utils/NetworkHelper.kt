package com.twitter.challenge.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(@ApplicationContext private val context: Context) {

    fun isNetworkConnected(): Boolean {
        // TODO: Use [NetworkCallbacks] for apps that target API 29+. Also listen for connectivity to retry call failed
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}