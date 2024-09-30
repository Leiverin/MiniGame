package com.example.test.ui.splash

import android.os.CountDownTimer
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentSplashBinding
import com.example.test.ui.base.BaseFragment

class SplashFragment: BaseFragment<FragmentSplashBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun initView() {
        moveToHome()
    }

    private fun moveToHome() {
        object : CountDownTimer(3000, 1){
            override fun onTick(millisUntilFinished: Long) {

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