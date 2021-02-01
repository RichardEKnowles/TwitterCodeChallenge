package com.twitter.challenge

import com.twitter.challenge.utils.celsiusToFahrenheit
import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class TemperatureConverterTests {
    @Test
    fun testCelsiusToFahrenheitConversion() {
        val precision = 0.01
        assertEquals((-50.0).celsiusToFahrenheit(), -58.0, precision)
        assertEquals((0.0).celsiusToFahrenheit(), 32.0, precision)
        assertEquals((10.0).celsiusToFahrenheit(), 50.0, precision)
        assertEquals((21.11).celsiusToFahrenheit(), 70.0, precision)
        assertEquals((37.78).celsiusToFahrenheit(), 100.0, precision)
        assertEquals((100.0).celsiusToFahrenheit(), 212.0, precision)
        assertEquals((1000.0).celsiusToFahrenheit(), 1832.0, precision)
    }
}