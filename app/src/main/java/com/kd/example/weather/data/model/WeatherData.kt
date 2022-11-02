package com.kd.example.weather.data.model

data class WeatherData(
    val locationName:String,
    val date:String,
    val weatherIcon:String,
    val weatherDescription:String,
    val weatherId:String,
    val tempMax:String,
    val tempMin:String
)