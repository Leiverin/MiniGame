package com.example.test.ui.play

import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentPlayBinding
import com.example.test.extension.loadImage
import com.example.test.extension.setBackPressListener
import com.example.test.ui.base.BaseFragment
import com.example.test.utils.pushdown.PushDownAnim

class PlayFragment: BaseFragment<FragmentPlayBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_play

    override fun initView() {
        binding.imgBg.loadImage(R.drawable.bg_home)
        initData()
        initEvents()
    }

    private fun initData() {

    }

    private fun initEvents() {
        setBackPressListener{
            findNavController().popBackStack()
        }
        PushDownAnim.setPushDownAnimTo(binding.btnBack).setOnClickListener {
            findNavController().popBackStack()
        }.setScale(0.9f)
        PushDownAnim.setPushDownAnimTo(binding.btnRuby).setOnClickListener {

        }.setScale(0.9f)
        PushDownAnim.setPushDownAnimTo(binding.btnShare).setOnClickListener {

        }.setScale(0.9f)
        PushDownAnim.setPushDownAnimTo(binding.btnSuggest).setOnClickListener {

        }.setScale(0.9f)
    }

}