package com.example.test.ui.splash

import android.os.CountDownTimer
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentSplashBinding
import com.example.test.extension.loadImage
import com.example.test.ui.base.BaseFragment

class SplashFragment: BaseFragment<FragmentSplashBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun initView() {
        binding.imgSplash.loadImage(R.drawable.splash)
        moveToHome()
    }

    private fun moveToHome() {
        binding.progress.setMax(3000)
        object : CountDownTimer(3000, 1){
            override fun onTick(millisUntilFinished: Long) {
                binding.progress.setProgress(3000 - millisUntilFinished)
            }
            override fun onFinish() {
                navToHome()
            }
        }.start()
    }

    private fun navToHome() {
        val directions = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}