package com.kd.example.weather.ui.base

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T:ViewDataBinding> : AppCompatActivity() {
    lateinit var binding:T
    abstract val layoutResourceId:Int
    abstract fun initView()
    abstract fun initObserver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        binding.lifecycleOwner = this
//        hideSystemUI()
        initView()
        initObserver()

    }

    fun hideSystemUI(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if(controller != null){
                // 상태바와 네비게이션을 사라지게 합니다.
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                // 특정 행동 ( 화면 끝을 스와이프 하는 등) 을 했을 때에만 시스템 바가 나타나도록 설정
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }else{
            /* R 버전 이하 */
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}