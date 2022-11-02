package com.kd.example.weather.data.repository

import com.kd.example.weather.BuildConfig
import com.kd.example.weather.data.model.ResponseWeather
import com.kd.example.weather.data.service.WeatherService
import com.kd.example.weather.util.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepositoryImp(
    private val weatherService: WeatherService
    ) : WeatherRepository {
    override suspend fun getCurrentWeather(lat:String, lon:String):ResultData<ResponseWeather>{
        return try{
            withContext(Dispatchers.IO){
                val data = weatherService.getCurrentWeather(
                    lat,
                    lon,
                    BuildConfig.OPEN_WEATHER_MAP_API_KEY
                ).execute().body()
                ResultData.Success(data)
            }
        }catch (e:Exception){
            ResultData.Error(e)
        }
    }

}