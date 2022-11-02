package com.kd.example.weather.data.repository

import android.util.Log
import com.kd.example.weather.BuildConfig
import com.kd.example.weather.data.model.WeatherData
import com.kd.example.weather.data.model.weather.current.ResponseCurrentWeather
import com.kd.example.weather.data.model.weather.daily.ResponseForecastWeather
import com.kd.example.weather.data.service.WeatherService
import com.kd.example.weather.util.ResultData
import com.kd.example.weather.util.TemperatureUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImp(
    private val weatherService: WeatherService
    ) : WeatherRepository {
    override suspend fun getCurrentWeather(lat:String, lon:String):ResultData<ResponseCurrentWeather>{
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

    override suspend fun getLocationForecastWeather(location:String):ResultData<ResponseForecastWeather>{
        return try{
            withContext(Dispatchers.IO){
                val data = weatherService.getForecastWeather(
                    locationName = location,
                    BuildConfig.OPEN_WEATHER_MAP_API_KEY
                ).execute().body()
                var weatherList = mutableListOf<WeatherData>()
                data?.let{
                    Log.e("repo","${data.cnt}")
                    Log.e("repo","${data.city}")
                    Log.e("repo","${data.cod}")
                    Log.e("repo","${data.message}")


                    data.list.forEachIndexed { index, dailyWeather ->
                        Log.e("repo","============ [$index] ============")
                        Log.e("repo","dt_txt   : ${dailyWeather.dt_txt}")
                        Log.e("repo","dt   : ${dailyWeather.dt}")
                        Log.e("repo","temp : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp)}")
                        Log.e("repo","temp_max : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_max)}")
                        Log.e("repo","temp_min : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_min)}")
                        Log.e("repo","weather size : ${dailyWeather.weather.size}")
                        Log.e("repo","weather description : ${dailyWeather.weather[0].description}")
                        Log.e("repo","weather icon : ${dailyWeather.weather[0].icon}")
                        Log.e("repo","weather id : ${dailyWeather.weather[0].id}")
                        Log.e("repo","weather main : ${dailyWeather.weather[0].main}")
                        Log.e("repo","weather description : ${dailyWeather.dt}")
                        Log.e("repo","====================================")
                    }

//                    for (index in 0 .. count){
//                        val dailyWeather = data.list[index*8]
//                        Log.e("repo","] ====== [$index] ======")
//                        Log.e("repo","dt_txt   : ${dailyWeather.dt_txt}")
//                        Log.e("repo","dt   : ${dailyWeather.dt}")
//                        Log.e("repo","temp : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp)}")
//                        Log.e("repo","temp_max : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_max)}")
//                        Log.e("repo","temp_min : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_min)}")
//                        Log.e("repo","weather size : ${dailyWeather.weather.size}")
//                        Log.e("repo","weather description : ${dailyWeather.weather[0].description}")
//                        Log.e("repo","weather icon : ${dailyWeather.weather[0].icon}")
//                        Log.e("repo","weather id : ${dailyWeather.weather[0].id}")
//                        Log.e("repo","weather main : ${dailyWeather.weather[0].main}")
//                        Log.e("repo","weather description : ${dailyWeather.dt}")
//                    }
                }
                ResultData.Success(data)
            }
        }catch (e:Exception){
            ResultData.Error(e)
        }
    }

}