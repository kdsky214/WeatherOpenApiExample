package com.kd.example.weather.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kd.example.weather.databinding.ItemWeatherDataViewBinding
import com.kd.example.weather.databinding.ItemWeatherSubjectViewBinding

class WeatherAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class SubjectViewHolder(binding:ItemWeatherSubjectViewBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(){

        }
    }
    class DataViewHolder(binding: ItemWeatherDataViewBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(){

        }
    }

}