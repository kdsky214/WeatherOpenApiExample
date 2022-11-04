package com.kd.example.weather.util

import kotlin.math.roundToInt

object TemperatureUtil {
    private const val absoluteTemp = 273.15


    //비교대상보다 크면 1
    /** compareTo
     * if(old > new) 1   min check
     * if(old < new) -1  max check
     * if(old == new) 0
     */
    private const val MaxValue:Int = -1
    private const val MinValue:Int = 1
    fun checkCompareMaxTemp(oldTemp:Double, newTemp:Double):Double
    = if(oldTemp.compareTo(newTemp) == MaxValue) newTemp else oldTemp
    fun checkCompareMinTemp(oldTemp:Double, newTemp:Double)
    = if(oldTemp.compareTo(newTemp) == MinValue) newTemp else oldTemp

    //화씨
    fun tempToFahrenheit(temp:Double) = roundOffValue((temp - absoluteTemp) * (9/5) + 32)

    //섭씨
    fun tempToCelsius(temp:Double) = roundOffValue(temp - absoluteTemp)

    //소수 2번째자리까지만 표현
    private fun roundOffValue(v:Double) = (v * 100.0).roundToInt() / 100.0
}