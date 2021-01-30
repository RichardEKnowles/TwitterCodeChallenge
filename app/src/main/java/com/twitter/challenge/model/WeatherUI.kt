package com.twitter.challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class WeatherUI(
        var name: String?,
        var date: Date?,
        var tempCelsius: Double?,
        var tempFahrenheit: Double?,
        var windSpeed: Double?,
        var showCloudIcon: Boolean = false
) : Parcelable