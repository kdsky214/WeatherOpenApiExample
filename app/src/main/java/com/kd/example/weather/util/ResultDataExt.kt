package com.kd.example.weather.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * RoomManagement
 * Class: ResultExt
 * Created by Sieun on 2021-03-27.
 *
 * Description: Extension for Result Class
 */

inline fun <T> ResultData<T>.updateOnSuccess(
    liveData: MutableLiveData<T>,
    onError: (e: Exception) -> Unit,
) {
    when (this) {
        is ResultData.Success -> liveData.value = this.data ?: return
        is ResultData.Error -> onError(e)
        else -> return
    }
}

inline fun <T> ResultData<T?>.updateOnSuccess(
    liveData: MutableLiveData<T?>,
    defaultValue: T,
    onError: (e: Exception) -> Unit,
) {
    when (this) {
        is ResultData.Success -> {
            data?.let {
                liveData.value = it
            } ?: run {
                liveData.value = defaultValue
            }
        }
        is ResultData.Error -> onError(e)
        else -> return
    }
}

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

inline fun <T> ResultData<T?>.getLiveData(onError: (e: Exception) -> Unit): LiveData<T> {
    val liveData = MutableLiveData<T>()

    when (this) {
        is ResultData.Success -> data?.let { liveData.postValue(it) }
        is ResultData.Error -> onError(e)
        else -> onError(Exception("ERROR"))
    }
    return liveData
}

inline fun <T> ResultData<T?>.getData(onError: (e: Exception) -> Unit): T? {
    when (this) {
        is ResultData.Success -> return data
        is ResultData.Error -> onError(e)
        else -> onError(Exception("ERROR"))
    }
    return null
}