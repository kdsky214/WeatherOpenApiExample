package com.kd.example.weather.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * RoomManagement
 * Class: ResultExt
 * Created by Sieun on 2021-03-27.
 *
 * Description: Extension for Result Class
 */

inline fun <T> ResultData<T?>.observe(
    onSuccess: (it: T?) -> Unit,
    onError: (e: Exception) -> Unit,
) {
    when (this) {
        is ResultData.Success -> data?.let { onSuccess(data) }
        is ResultData.Error -> onError(e)
        else -> return
    }
}

inline fun <T> ResultData<T?>.observe(
    onSuccess: (it: T) -> Unit,
    defaultValue: T,
    onError: (e: Exception) -> Unit,
) {
    when (this) {
        is ResultData.Success -> {
            data?.let {
                onSuccess(data)
            } ?: run {
                onSuccess(defaultValue)
            }
        }
        is ResultData.Error -> onError(e)
        else -> return
    }
}

inline fun <T> ResultData<T?>.getData(onError: (e: Exception) -> Unit): T? {
    when (this) {
        is ResultData.Success -> return data
        is ResultData.Error -> onError(e)
        else -> onError(Exception("ERROR"))
    }
    return null
}