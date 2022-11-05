package com.kd.example.weather.data.repository

import android.util.Log
import com.kd.example.weather.BuildConfig
import com.kd.example.weather.application.WeatherApplication
import com.kd.example.weather.data.model.WeatherModel
import  com.kd.example.weather.data.model.weather.current.ResponseCurrentWeather
import com.kd.example.weather.data.service.WeatherService
import com.kd.example.weather.data.model.ResultData
import com.kd.example.weather.util.DateUtil
import com.kd.example.weather.util.TemperatureUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WeatherRepositoryImp(
    private val weatherService: WeatherService
    ) : WeatherRepository {
    override suspend fun getCurrentWeather(lat:String, lon:String): ResultData<ResponseCurrentWeather> {
        return try{
            withContext(Dispatchers.IO){
                val data = weatherService.getCurrentWeather(
                    lat,
                    lon,
                    BuildConfig.OPEN_WEATHER_MAP_API_KEY
                )
//                WeatherApplication.sendLogPrettyJson(data)
                ResultData.Success(data)
            }
        }catch (e:Exception){
            ResultData.Error(e)
        }
    }

    override suspend fun getLocationForecastWeather(location:String): ResultData<List<WeatherModel>> {
        return try{
            withContext(Dispatchers.IO){
//                val data = weatherService.getForecastWeather(
//                    locationName = location,
//                    BuildConfig.OPEN_WEATHER_MAP_API_KEY
//                ).execute().body()
                val data = weatherService.getForecastWeather(
                    locationName = location,
                    BuildConfig.OPEN_WEATHER_MAP_API_KEY
                )
                var weatherList = mutableListOf<WeatherModel>()
                var checkDtText = ""
                var number = 0
                data?.let{
                    checkDtText = data.list[0].dt_txt
                    data.list.forEachIndexed { index, dailyWeather ->
                        val changeDateText = DateUtil.changeDateFormatString(dailyWeather.dt_txt,
                            DateUtil.DEFAULT_DATE_FORMAT,
                            DateUtil.DATE_FORMAT,
                            Locale.UK)
                        val checkHourTime = DateUtil.getDateFormatToHour(dailyWeather.dt_txt)
//                        Log.e("repo","dt   : ${dailyWeather.dt_txt}  ${checkHourTime}")
                        if(checkDtText != changeDateText && checkHourTime > 3){
                            changeDateText?.let{
                                checkDtText = it
                                weatherList.add(WeatherModel(
                                    index = number,
                                    locationName = data.city.name,
                                    date = changeDateText,
                                    weatherIcon = dailyWeather.weather[0].icon,
                                    weatherDescription= dailyWeather.weather[0].description,
                                    weatherMain = dailyWeather.weather[0].main,
                                    weatherId = dailyWeather.weather[0].id,
                                    tempMax = TemperatureUtil.tempToCelsius(dailyWeather.main.temp_max),
                                    tempMin = TemperatureUtil.tempToCelsius(dailyWeather.main.temp_min),
                                ))
                                number++
                            }
//                            Log.e("repo","============ [$number] ============")
                        }else if(checkDtText == changeDateText){
                            //max min temp check
                            val weatherData = weatherList[weatherList.size - 1]
                            val newTempMax = TemperatureUtil.tempToCelsius(dailyWeather.main.temp_max)
                            val newTempMin = TemperatureUtil.tempToCelsius(dailyWeather.main.temp_min)
                            weatherData.tempMax = TemperatureUtil.checkCompareMaxTemp(weatherData.tempMax, newTempMax)
                            weatherData.tempMin = TemperatureUtil.checkCompareMinTemp(weatherData.tempMin, newTempMin)
                        }//end else

//                        Log.e("repo","weather main : ${dailyWeather.weather[0].main}")
//                        Log.e("repo","============ [$index] ============")
//                        Log.e("repo","============ [$checkDtText] ============")
//                        Log.e("repo","============ [${DateUtil.changeDateFormatString(dailyWeather.dt_txt, DateUtil.DEFAULT_DATE_FORMAT, DateUtil.DATE_FORMAT)}] ============")
//                        Log.e("repo","dt   : ${dailyWeather.dt}")
//                        Log.e("repo","temp : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp)}")
//                        Log.e("repo","temp_max : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_max)}")
//                        Log.e("repo","temp_min : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_min)}")
//                        Log.e("repo","weather size : ${dailyWeather.weather.size}")
//                        Log.e("repo","weather description : ${dailyWeather.weather[0].description}")
//                        Log.e("repo","weather icon : ${dailyWeather.weather[0].icon}")
//                        Log.e("repo","weather main : ${dailyWeather.weather[0].main}")
//                        Log.e("repo","weather id : ${dailyWeather.weather[0].id}")
//                        Log.e("repo","weather main : ${dailyWeather.weather[0].main}")
//                        Log.e("repo","weather description : ${dailyWeather.dt}")
//                        Log.e("repo","====================================")
                    }//forEachIndexed

//                    for (index in 0 .. count){
//                        val dailyWeather = data.list[index*8]
//                        Log.e("repo","] ====== [$index] ======")
//                        Log.e("repo","dt_txt   : ${dailyWeather.dt_txt}")
//                        Log.e("repo","dt   : ${dailyWeather.dt}")
//                        Log.e("repo","temp : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp)}")
//                        Log.e("repo","temp_max : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_max)}")
//                        Log.e("repo","temp_min : ${TemperatureUtil.tempToCelsius(dailyWeather.main.temp_min)}")
//                        Log.e("repo","weather size : ${dailyWeather.weather.size}")
//                        Log.e("repo","weather description : ${dailyWeather.weather[0].description}")
//                        Log.e("repo","weather icon : ${dailyWeather.weather[0].icon}")
//                        Log.e("repo","weather id : ${dailyWeather.weather[0].id}")
//                        Log.e("repo","weather main : ${dailyWeather.weather[0].main}")
//                        Log.e("repo","weather description : ${dailyWeather.dt}")
//                    }
                }
                ResultData.Success(weatherList)
//                ResultData.Error(Exception("ee"))

            }
        }catch (e:Exception){
            ResultData.Error(e)
        }
    }

}