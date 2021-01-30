package com.twitter.challenge.repository

import com.twitter.challenge.network.ApiHelper
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getCurrentWeather() = apiHelper.getCurrentWeather()
    suspend fun getFutureWeather(nThDay: Int) = apiHelper.getFutureWeather(nThDay)
}