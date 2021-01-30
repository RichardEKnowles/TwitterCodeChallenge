package com.twitter.challenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponseDto(@SerializedName("name") val name: String?,
                              @SerializedName("weather") val weather: WeatherDto?,
                              @SerializedName("wind") val wind: WindDto?,
                              @SerializedName("clouds") val clouds: CloudsDto?
) : Parcelable

@Parcelize
data class WeatherDto(@SerializedName("temp") val temp: Double?,
                      @SerializedName("pressure") val pressure: Int?
) : Parcelable

@Parcelize
data class WindDto(@SerializedName("speed") val speed: Double,
                   @SerializedName("deg") val directionInDegrees: Int
) : Parcelable

@Parcelize
data class CloudsDto(@SerializedName("cloudiness") val cloudiness: Int
) : Parcelable