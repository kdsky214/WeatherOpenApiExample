package com.kd.example.weather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kd.example.weather.application.WeatherApplication
import com.kd.example.weather.data.model.WeatherModel
import com.kd.example.weather.data.repository.WeatherRepository
import com.kd.example.weather.data.model.observe
import com.kd.example.weather.data.type.LocationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val TAG = "MainViewModel"

    //weather livedata
    private var _weatherList:MutableList<WeatherModel> = mutableListOf()
    private var _weatherListMutableLiveData:MutableLiveData<List<WeatherModel>> = MutableLiveData()
    val weatherListMutableLiveData:LiveData<List<WeatherModel>> = _weatherListMutableLiveData
//    private var _londonWeatherMutableLiveData:MutableLiveData<List<WeatherData>> = MutableLiveData()
//    val londonWeatherMutableLiveData:LiveData<List<WeatherData>> = _londonWeatherMutableLiveData
//    private var _seoulWeatherMutableLiveData:MutableLiveData<List<WeatherData>> = MutableLiveData()
//    val seoulWeatherMutableLiveData:LiveData<List<WeatherData>> = _seoulWeatherMutableLiveData
//    private var _chicagoWeatherMutableLiveData:MutableLiveData<List<WeatherData>> = MutableLiveData()
//    val chicagoWeatherMutableLiveData:LiveData<List<WeatherData>> = _chicagoWeatherMutableLiveData
    /*위도 경도로 현재날씨 정보 가져오기*/
    fun getCurrentWeather(){
        val lat = "42.9834"
        val lon = "-81.233"
        viewModelScope.launch(Dispatchers.IO){
            weatherRepository.getCurrentWeather(lat, lon).observe(
                onSuccess ={ response->
                }, onError = {
                    Log.e(TAG," onError = $it")
                }
            )
        }
    }

    /*지역 주간날씨 정보 가져오기*/
    fun getForecastWeather(){
        viewModelScope.launch(Dispatchers.IO){
            //Seoul data
            weatherRepository.getLocationForecastWeather(LocationType.Seoul.name).observe(
                onSuccess ={ response->
                    response?.let{ _weatherList.addAll(it) }
                }, onError = {
                    //TODO:예외처리
                    Log.e(TAG," Seoul onError = $it")
                }
            )
            //London data
            weatherRepository.getLocationForecastWeather(LocationType.London.name).observe(
                onSuccess ={ response->
                    response?.let{ _weatherList.addAll(it) }
                }, onError = {
                    //TODO:예외처리
                    Log.e(TAG," London onError = $it")
                }
            )

            //Chicago data
            weatherRepository.getLocationForecastWeather(LocationType.Chicago.name).observe(
                onSuccess ={ response->
                    response?.let{ _weatherList.addAll(it) }
                }, onError = {
                    //TODO:예외처리
                    Log.e(TAG,"Chicago onError = $it")
                }
            )

            //list add livedata
            viewModelScope.launch(Dispatchers.Main){
                _weatherListMutableLiveData.value = _weatherList
            }

        }
    }
}