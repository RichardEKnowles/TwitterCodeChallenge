package com.twitter.challenge.data

import com.twitter.challenge.model.WeatherResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("current.json")
    suspend fun getCurrentWeather(): Response<WeatherResponseDto>

    @GET("future_{nthDay}.json")
    suspend fun getFutureWeather(@Path("nthDay") nthDay: Int): Response<WeatherResponseDto>
}