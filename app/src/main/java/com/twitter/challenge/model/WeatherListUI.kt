package com.twitter.challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherListUI(
    var tempStandardDeviation: Pair<Double, Double>?,
    var weatherList: List<WeatherUI>?
) : Parcelable