package com.kd.example.weather.data.type

import com.kd.example.weather.R

sealed class WeatherType(val mainName:String, val drawableId:Int) {

    object Sunny:WeatherType("Clear", R.drawable.wi_day_sunny)
    object Rain:WeatherType("Rain", R.drawable.wi_day_rain)
    object Cloud:WeatherType("Clouds", R.drawable.wi_day_cloudy)
    object Snow:WeatherType("Snow", R.drawable.wi_day_snow)



}