package com.kd.example.weather.data.repository

import com.kd.example.weather.data.model.weather.current.ResponseCurrentWeather
import com.kd.example.weather.data.model.ResultData
import com.kd.example.weather.data.model.WeatherModel

interface WeatherRepository {
    /* 현재 날씨 (위도, 경도)*/
    suspend fun getCurrentWeather(lat:String, lon:String): ResultData<ResponseCurrentWeather>

    /*  */
    suspend fun getLocationForecastWeather(location:String): ResultData<List<WeatherModel>>

}