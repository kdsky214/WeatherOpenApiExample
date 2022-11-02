package com.kd.example.weather.data.repository

import com.kd.example.weather.BuildConfig
import com.kd.example.weather.data.model.WeatherModel
import com.kd.example.weather.data.service.WeatherService
import com.kd.example.weather.util.ResultData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepositoryImp(
    private val weatherService: WeatherService
    ) : WeatherRepository {

    override suspend fun getCurrentWeather(
        lat:String,
        lon:String,
        result:(ResultData<WeatherModel>)->Unit){
        try{
            weatherService.getCurrentWeather(
                lat,
                lon,
                BuildConfig.OPEN_WEATHER_MAP_API_KEY
            ).enqueue(object :Callback<WeatherModel>{
                override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                    if(response.isSuccessful){
                        /* Success */
                        result(ResultData.Success(response.body()))
                    }else{
                        /* is not Successful */
                        result(ResultData.Error(Exception("Response isNotSuccessful")))
                    }
                }
                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                    /* failed */
                    result(ResultData.Error(Exception(t)))
                }
            })
        }catch (e:Exception){
            /* Exception */
            result(ResultData.Error(e))
        }

    }

}