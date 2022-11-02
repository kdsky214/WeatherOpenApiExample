package com.kd.example.weather.ui.activity

import androidx.activity.viewModels
import com.kd.example.weather.R
import com.kd.example.weather.databinding.ActivityMainBinding
import com.kd.example.weather.ui.base.BaseActivity
import com.kd.example.weather.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel:MainViewModel by viewModels()
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override fun initView() {

    }

    override fun initObserver() {
        mainViewModel.getCurrentWeather()
    }



}