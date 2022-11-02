package com.kd.example.weather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import com.kd.example.weather.data.model.WeatherData
import com.kd.example.weather.data.repository.WeatherRepository
import com.kd.example.weather.util.observe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val TAG = "MainViewModel"

    private var weatherDataList:MutableList<WeatherData> = mutableListOf()

    fun getCurrentWeather(){
        val lat = "42.9834"
        val lon = "-81.233"
        viewModelScope.launch(Dispatchers.IO){
            weatherRepository.getCurrentWeather(lat, lon).observe(
                onSuccess ={ response->
                    val gson = GsonBuilder()
                        .setPrettyPrinting()
                        .create()

                    val jsonOutput = gson.toJson(response)
                    Log.e(TAG, "res = $jsonOutput")

                    response?.let{

                    }
                }, onError = {
                    Log.e(TAG," onError = $it")
                }
            )
        }
    }

    //지역 주간날씨 정보 가져오기
    fun getForecastWeather(){
        viewModelScope.launch(Dispatchers.IO){
            weatherRepository.getLocationForecastWeather("London").observe(
                onSuccess ={ response->
                    val gson = GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                    val jsonOutput = gson.toJson(response)
//                    Log.e(TAG, "res = $jsonOutput")

                }, onError = {
                    Log.e(TAG," onError = $it")
                }
            )
        }
    }
}