package com.kd.example.weather.data.repository

import com.kd.example.weather.data.model.ResponseWeather
import com.kd.example.weather.util.ResultData

interface WeatherRepository {
    suspend fun getCurrentWeather(lat:String, lon:String):ResultData<ResponseWeather>
}