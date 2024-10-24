package com.example.test.ui

import androidx.navigation.fragment.NavHostFragment
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.ui.base.BaseActivity
import com.example.test.utils.DataManager

class MainActivity: BaseActivity<ActivityMainBinding>() {

    override fun layoutRes(): Int = R.layout.activity_main

    override fun initView() {
        DataManager.getInstance().getListAssets(this)
        setUpNav()
    }

    private fun setUpNav() {
        // Set up de cho vao man splash dau tien
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fcvMain.id) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_nav)
        graph.setStartDestination(R.id.splashFragment)
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }

}