package com.kd.example.weather.data.service

import com.kd.example.weather.data.model.weather.current.ResponseCurrentWeather
import com.kd.example.weather.data.model.weather.daily.ResponseForecastWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
  * OpenWeatherMap API
 **/
interface WeatherService {
    /*위도, 경도로 현재 날씨 가져오기*/
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("APPID") APPID: String)
            : Call<ResponseCurrentWeather>


    /*도시 이름으로 주간 날씨 가져오기*/
    @GET("data/2.5/forecast")
    fun getForecastWeather(
        @Query("q") locationName: String,
        @Query("APPID") appId: String)
    : Call<ResponseForecastWeather>

}