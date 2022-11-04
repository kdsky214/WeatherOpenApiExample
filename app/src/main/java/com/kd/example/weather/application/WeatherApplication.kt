package com.kd.example.weather.application

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() , LifecycleObserver {
    companion object{
        const val DEFAULT_WEATHER_ICON_URL = "http://openweathermap.org/img/wn/"
    }
    override fun onCreate() {
        super.onCreate()
    }
}