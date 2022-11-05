package com.kd.example.weather.application

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.google.gson.GsonBuilder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() , LifecycleObserver {
    companion object{
        const val DEFAULT_WEATHER_ICON_URL = "http://openweathermap.org/img/wn/"


        fun sendLogPrettyJson(jsonStr:Any){
            val gson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            Log.e("log","=== result ===  \n ${gson.toJson(jsonStr)}")
        }
    }
    override fun onCreate() {
        super.onCreate()
    }


}