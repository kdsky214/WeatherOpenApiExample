package com.kd.example.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.kd.example.weather.application.WeatherApplication
import com.kd.example.weather.data.model.WeatherModel
import com.kd.example.weather.data.type.WeatherType
import com.kd.example.weather.databinding.ItemWeatherDataViewBinding
import com.kd.example.weather.databinding.ItemWeatherTitleViewBinding
import kotlin.math.roundToInt

//기본으로 6개의 데이터를 표현
class WeatherAdapter(
    private val numberOfDay:Int = 6
) : RecyclerView.Adapter<ViewHolder>() {
    val isUseIconUrl = true
    //list
    private var weatherDataList:MutableList<WeatherModel> = mutableListOf()
    //type
    enum class ViewType{
        TITLE,
        CONTENT
    }

    override fun getItemViewType(position: Int): Int {
        //title position check
        val titlePosition = position % (numberOfDay+1)
        return if(titlePosition == 0){
            ViewType.TITLE.ordinal
        }else{
            ViewType.CONTENT.ordinal
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when(viewType){
            ViewType.TITLE.ordinal ->{
                TitleViewHolder(ItemWeatherTitleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            ViewType.CONTENT.ordinal->{
                ContentViewHolder(ItemWeatherDataViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else->{
                //else
                ContentViewHolder(ItemWeatherDataViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contentNumber = (position / (numberOfDay+1))
        var dataPosition = (position - contentNumber)
        if((position % (numberOfDay+1)) == 0){
            //title
            /* 가장 첫 데이터의 지역이름을 Title로 사용*/
            (holder as TitleViewHolder).onBind(weatherDataList[dataPosition])
        }else{
            //data
            dataPosition -= 1
//            Log.e("recy","position($position) contentNumber($contentNumber) data bind($dataPosition)")
            (holder as ContentViewHolder).onBind(weatherDataList[dataPosition])

            if(isUseIconUrl){
                /*Icon Image URL로 가져오기*/
                val iconUrl = "${WeatherApplication.DEFAULT_WEATHER_ICON_URL}${weatherDataList[dataPosition].weatherIcon}@2x.png"
                holder.setWeatherImageUrl(iconUrl)

            }else{
                /*Icon Image SVG 이미지로 가져오기*/
                holder.setImageDrawable(weatherDataList[dataPosition].weatherMain)
            }

        }
    }

    /* 데이터 사이즈 + 추가되는 title 수 */
    override fun getItemCount(): Int = weatherDataList.size + (weatherDataList.size/numberOfDay)

    fun itemAddAll(list:List<WeatherModel>){
        weatherDataList.clear()
        weatherDataList.addAll(list)
        notifyDataSetChanged()
    }

    class TitleViewHolder(
        private val binding:ItemWeatherTitleViewBinding
        ):ViewHolder(binding.root){
        fun onBind(data:WeatherModel){
            binding.tvLocationName.text = data.locationName
        }
    }
    class ContentViewHolder(
        private val binding: ItemWeatherDataViewBinding
        ):ViewHolder(binding.root){
        fun onBind(data:WeatherModel){
//            Log.e("ContentViewHolder", "data : ${data.index}")
            binding.tvDate.text = when(data.index){
                0-> "Today"
                1-> "Tomorrow"
                else->data.date
            }
            binding.tvWeatherDescription.text = data.weatherDescription
            binding.tvTempMax.text = "Max : ${data.tempMax.roundToInt()} °C"
            binding.tvTempMin.text = "Min : ${data.tempMin.roundToInt()} °C"
        }

        //Url Image 사용
        fun setWeatherImageUrl(url:String){
            Glide.with(binding.root).load(url).into(binding.imgWeather)
        }

        //SVG Image 사용
        fun setImageDrawable(weatherMain:String){
            when(weatherMain){
                WeatherType.Sunny.mainName-> binding.imgWeather.setImageResource(WeatherType.Sunny.drawableId)
                WeatherType.Rain.mainName->  binding.imgWeather.setImageResource(WeatherType.Rain.drawableId)
                WeatherType.Cloud.mainName-> binding.imgWeather.setImageResource(WeatherType.Cloud.drawableId)
                WeatherType.Snow.mainName->  binding.imgWeather.setImageResource(WeatherType.Snow.drawableId)
            }
        }

    }
}