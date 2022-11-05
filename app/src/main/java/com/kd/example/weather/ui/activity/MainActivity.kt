package com.kd.example.weather.ui.activity

import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.kd.example.weather.R
import com.kd.example.weather.databinding.ActivityMainBinding
import com.kd.example.weather.ui.adapter.WeatherAdapter
import com.kd.example.weather.ui.adapter.decoration.CustomDivisionItemDecoration
import com.kd.example.weather.ui.base.BaseActivity
import com.kd.example.weather.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel:MainViewModel by viewModels()
    var weatherAdapter = WeatherAdapter()
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override fun initView() {
        binding.recyclerviewWeather.apply {
            //layoutmanager Style
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            //높이 일정한사이즈
            this.setHasFixedSize(true)
            //ItemDecoration
//            val decoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
//            decoration.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_content))
//            this.addItemDecoration(decoration)
//            this.addItemDecoration(CustomDivisionItemDecoration(
//                this@MainActivity,
//                R.drawable.recyclerview_divider_title,
//                R.drawable.recyclerview_divider_content,
//            ))
            this.adapter = weatherAdapter

        }

    }

    override fun initObserver() {
        mainViewModel.weatherListMutableLiveData.observe(this){
            //Observe
            weatherAdapter.itemAddAll(it)
        }
//        mainViewModel.getCurrentWeather()
        mainViewModel.getForecastWeather()
    }

}