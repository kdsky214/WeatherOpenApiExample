package com.kd.example.weather.data.model.weather.daily

data class ResponseForecastWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyWeather>,
    val message: Int
)