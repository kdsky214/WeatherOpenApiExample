package com.kd.example.weather.util

sealed class ResultData<out T> {
    data class Success<out T>(val data: T?) : ResultData<T>()
    data class Error(val e: Exception) : ResultData<Nothing>()
}