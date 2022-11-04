package com.kd.example.weather.data.model

sealed class ResultData<out T> {
    data class Success<out T>(val data: T?) : ResultData<T>()
    data class Error(val e: Exception) : ResultData<Nothing>()
}