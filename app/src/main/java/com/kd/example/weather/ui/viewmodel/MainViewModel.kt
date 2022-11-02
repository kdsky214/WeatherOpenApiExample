package com.kd.example.weather.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kd.example.weather.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val TAG = "MainViewModel"
    fun getCurrentWeather(){
        viewModelScope.launch(Dispatchers.IO){
            val lat = "42.9834"
            val lon = "-81.233"
            weatherRepository.getCurrentWeather(lat, lon){
                Log.e(TAG, "res = $it")
            }

        }

    }
}