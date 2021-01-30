package com.twitter.challenge.data

import com.twitter.challenge.model.WeatherResponseDto
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getCurrentWeather(): Response<WeatherResponseDto> = apiService.getCurrentWeather()
    override suspend fun getFutureWeather(nthDay: Int): Response<WeatherResponseDto> = apiService.getFutureWeather(nthDay)
}