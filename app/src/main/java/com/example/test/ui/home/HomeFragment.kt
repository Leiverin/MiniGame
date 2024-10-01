package com.example.test.ui.home

import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.extension.loadImage
import com.example.test.extension.setBackPressListener
import com.example.test.ui.base.BaseFragment
import com.example.test.utils.pushdown.PushDownAnim

class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun initView() {
        binding.imgHome.loadImage(R.drawable.bg_home)
        initData()
        initEvents()
    }

    private fun initData() {
        binding.tvLevelCurrent.text = MMKVUtils.getListPlayed().size.toString()
    }

    private fun initEvents() {
        setBackPressListener{}
        PushDownAnim.setPushDownAnimTo(binding.btnPlay).setOnClickListener {
            navToPlay()
        }.setScale(0.8f)
        PushDownAnim.setPushDownAnimTo(binding.btnAddCoin).setOnClickListener {
        }.setScale(0.8f)
        PushDownAnim.setPushDownAnimTo(binding.btnSound).setOnClickListener {
        }.setScale(0.8f)
        PushDownAnim.setPushDownAnimTo(binding.btnInfo).setOnClickListener {
        }.setScale(0.8f)
        PushDownAnim.setPushDownAnimTo(binding.btnEmail).setOnClickListener {
        }.setScale(0.8f)
        PushDownAnim.setPushDownAnimTo(binding.btnUser).setOnClickListener {
        }.setScale(0.8f)
    }

    private fun navToPlay(){
        val directions = HomeFragmentDirections.actionHomeFragmentToPlayFragment()
        findNavController().navigate(directions)
    }

}