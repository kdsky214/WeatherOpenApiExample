package com.kd.example.weather.di

import com.kd.example.weather.data.repository.WeatherRepository
import com.kd.example.weather.data.repository.WeatherRepositoryImp
import com.kd.example.weather.data.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {
    const val BASE_WEATHER_URL = "http://api.openweathermap.org/"
    const val WEATHER_API_KEY = "172a4ab26121b472d6c1099e711ccd44"
    //service
    @Singleton
    @Provides
    fun providesWeatherService(
        @Named("retrofit") retrofit: Retrofit
    ): WeatherService = retrofit.create(WeatherService::class.java)

    //repository
    @Singleton
    @Provides
    fun provideWeatherRepository(
        weatherService: WeatherService
    ): WeatherRepository = WeatherRepositoryImp(weatherService)
}