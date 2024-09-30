package com.example.test.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V: ViewDataBinding>: AppCompatActivity() {

    protected lateinit var binding: V

    @LayoutRes
    abstract fun layoutRes(): Int

    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes())
        binding.lifecycleOwner = this
        initView()
    }

    override fun onResume() {
        super.onResume()
        hideNavigationSystem()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        hideNavigationSystem()
    }

    private fun hideNavigationSystem(){
        WindowCompat.getInsetsController(window, binding.root).let {
            it.hide(WindowInsetsCompat.Type.navigationBars())
            it.hide(WindowInsetsCompat.Type.statusBars())
            it.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}