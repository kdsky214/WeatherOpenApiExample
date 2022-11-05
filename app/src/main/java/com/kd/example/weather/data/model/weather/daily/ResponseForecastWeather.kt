package  com.kd.example.weather.data.model.weather.daily


data class ResponseForecastWeather(
    var city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyWeather>,
    val message: Int
)