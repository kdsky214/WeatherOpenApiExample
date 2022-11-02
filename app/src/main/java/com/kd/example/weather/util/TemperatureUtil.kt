package com.kd.example.weather.util

import kotlin.math.roundToInt

object TemperatureUtil {
    private const val absoluteTemp = 273.15

    //화씨
    fun tempToFahrenheit(temp:Double) = roundOffValue((temp - absoluteTemp) * (9/5) + 32)

    //섭씨
    fun tempToCelsius(temp:Double) = roundOffValue(temp - absoluteTemp)

    //소수 2번째자리까지만 표현
    private fun roundOffValue(v:Double) = (v * 100.0).roundToInt() / 100.0
}