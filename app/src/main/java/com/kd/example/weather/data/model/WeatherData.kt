package com.kd.example.weather.data.model

import com.kd.example.weather.data.type.LocationType

data class WeatherData(
    val locationType: LocationType,
    val date:String,
    val type:String,
    val weatherIcon:String,
    val weatherDescription:String,
    val tempMax:String,
    val tempMin:String
)
{
}