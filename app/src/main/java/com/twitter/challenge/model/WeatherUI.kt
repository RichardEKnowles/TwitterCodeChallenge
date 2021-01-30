package com.twitter.challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherUI(
    var name: String?,
    var tempCelsius: Double?,
    var tempFahrenheit: Double?,
    var windSpeed: Double?,
    var showCloudIcon: Boolean = false
) : Parcelable