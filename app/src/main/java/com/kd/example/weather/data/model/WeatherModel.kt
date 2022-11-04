package com.kd.example.weather.data.model

data class WeatherModel(
    val index:Int,
    val locationName:String,
    val date:String,
    val weatherIcon:String,
    val weatherDescription:String,
    val weatherMain:String,
    val weatherId:Int,
    var tempMax:Double,
    var tempMin:Double
)