package com.kd.example.weather.data.model

sealed class ResultData<out T> {
    data class Success<out T>(val data: T?) : ResultData<T>()
    data class Error(val e: Exception) : ResultData<Nothing>()
}

inline fun <T> ResultData<T?>.observe(
    onSuccess: (it: T?) -> Unit,
    onError: (e: Exception) -> Unit,
) {
    when (this) {
        is ResultData.Success -> data?.let { onSuccess(data) }
        is ResultData.Error -> onError(e)
    }
}