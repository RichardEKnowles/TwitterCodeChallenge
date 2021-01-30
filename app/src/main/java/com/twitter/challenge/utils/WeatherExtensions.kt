package com.twitter.challenge.utils

import com.twitter.challenge.model.WeatherResponseDto
import com.twitter.challenge.model.WeatherUI

/**
 * Converts temperature in Celsius to temperature in Fahrenheit.
 *
 * @return Temperature in Fahrenheit.
 */
fun Double.celsiusToFahrenheit(): Double {
    return this * 1.8f + 32
}

/**
 * Transforms [WeatherResponseDto] to [WeatherUI].
 *
 * @return [WeatherUI].
 */
fun WeatherResponseDto.toWeatherUIModel(): WeatherUI {
    val tempCelsius = weather?.temp
    return WeatherUI(
        name = name,
        tempCelsius = tempCelsius,
        tempFahrenheit = tempCelsius?.celsiusToFahrenheit(),
        windSpeed = wind?.speed,
        showCloudIcon = clouds != null && clouds.cloudiness > 50
    )
}

/**
 * Calculates standard deviation for temperatures in Celsius and Fahrenheit.
 *
 * @return [Pair] where first is standard deviation for Celsius and second is for Fahrenheit.
 */
fun List<WeatherUI>.calcTempStandardDeviation(): Pair<Double, Double> {
    // TODO: Simplify this so that we don't need to map twice
    val celsiusStandardDeviation = mapNotNull { it.tempCelsius }.toDoubleArray().standardDeviation()
    val fahrenheitStandardDeviation = mapNotNull { it.tempFahrenheit }.toDoubleArray().standardDeviation()
    return celsiusStandardDeviation to fahrenheitStandardDeviation
}