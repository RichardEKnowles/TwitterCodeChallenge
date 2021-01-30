package com.twitter.challenge.utils

import kotlin.math.sqrt

fun DoubleArray.standardDeviation(): Double {
    if (size <= 1) return Double.NaN
    val average = average()
    var sum = 0.0
    for (element in this) {
        sum += (element - average) * (element - average)
    }
    return sqrt(sum / (size - 1))
}