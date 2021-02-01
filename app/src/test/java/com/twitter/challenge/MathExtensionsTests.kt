package com.twitter.challenge

import com.twitter.challenge.utils.standardDeviation
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MathExtensionsTests {

    @Test
    fun testStandardDeviationForZeros() {
        assertEquals(doubleArrayOf(0.0, 0.0).standardDeviation(), 0.0)
    }

    @Test
    fun testStandardDeviationForOneElement() {
        assertEquals(doubleArrayOf(5.2).standardDeviation(), Double.NaN)
    }

    @Test
    fun testStandardDeviationForEmptyList() {
        assertEquals(doubleArrayOf().standardDeviation(), Double.NaN)
    }

    @Test
    fun testStandardDeviationForAllPositiveElements() {
        val testList = arrayListOf(
                doubleArrayOf(10.0, 12.0, 23.0, 23.0, 16.0, 23.0, 21.0, 16.0) to 5.237,
                doubleArrayOf(23.1, 99.2, 45.6, 17.8, 9.0) to 36.296,
                doubleArrayOf(100.1, 38.2) to 43.769
        )

        for (item in testList) {
            assertEquals(item.first.standardDeviation(), item.second, PRECISION)
        }
    }

    @Test
    fun testStandardDeviationForAllNegativeElements() {
        val testList = arrayListOf(
                doubleArrayOf(-10.0, -12.0, -23.0, -23.0, -16.0, -23.0, -21.0, -16.0) to 5.237,
                doubleArrayOf(-23.1, -99.2, -45.6, -17.8, -9.0) to 36.296,
                doubleArrayOf(-100.1, -38.2) to 43.769
        )

        for (item in testList) {
            assertEquals(item.first.standardDeviation(), item.second, PRECISION)
        }
    }

    @Test
    fun testStandardDeviationForPositiveAndNegativeElements() {
        val testList = arrayListOf(
                doubleArrayOf(10.0, -12.0, -23.0, 23.0, -16.0, -23.0, 21.0, -16.0) to 19.353,
                doubleArrayOf(-23.1, 99.2, -45.6, -17.8, 9.0) to 56.474,
                doubleArrayOf(-100.1, 38.2) to 97.792
        )

        for (item in testList) {
            assertEquals(item.first.standardDeviation(), item.second, PRECISION)
        }
    }

    @Test
    fun testStandardDeviationForPositiveAndNegativeElementsAndZeros() {
        val testList = arrayListOf(
                doubleArrayOf(10.0, -12.0, 0.0, 0.0, 0.0, -23.0, 21.0, -16.0) to 14.242,
                doubleArrayOf(-23.1, 0.0, -45.6, -17.8, 9.0) to 21.269,
                doubleArrayOf(-100.1, 38.2, 0.0) to 71.421
        )

        for (item in testList) {
            assertEquals(item.first.standardDeviation(), item.second, PRECISION)
        }
    }

    companion object {
        private const val PRECISION = 0.01
    }
}