package com.kd.example.weather.application

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication : Application() , LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
    }
}