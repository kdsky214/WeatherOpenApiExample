package com.kd.example.weather.data.service

import com.kd.example.weather.data.model.ResponseWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingService {
    @GET("weather?")
    fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("APPID") APPID: String)
            : Call<ResponseWeather>

}