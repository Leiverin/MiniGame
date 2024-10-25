package com.example.test.ui.ruby

import com.example.test.R
import com.example.test.databinding.FragmentMakeRubyBinding
import com.example.test.ui.base.BaseFragment

class MakeRubyFragment: BaseFragment<FragmentMakeRubyBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_make_ruby

    override fun initView() {
        initRv()
    }

    private fun initRv() {

    }

}