package com.twitter.challenge.data

import com.twitter.challenge.model.WeatherResponseDto
import retrofit2.Response

interface ApiHelper {

    suspend fun getCurrentWeather(): Response<WeatherResponseDto>
    suspend fun getFutureWeather(nthDay: Int): Response<WeatherResponseDto>
}