package com.example.test.ui.ruby

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.databinding.FragmentMakeRubyBinding
import com.example.test.extension.showDialogIntroduction
import com.example.test.extension.toast
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.ruby.adapters.EarnRubyAdapter
import com.example.test.utils.DataManager
import com.example.test.utils.pushdown.PushDownAnim

class MakeRubyFragment: BaseFragment<FragmentMakeRubyBinding>() {
    private val adapter = EarnRubyAdapter()

    override fun layoutRes(): Int = R.layout.fragment_make_ruby

    override fun initView() {
        initRv()
        initEvents()
    }

    private fun initEvents() {
        PushDownAnim.setPushDownAnimTo(binding.btnBack).setOnClickListener {
            findNavController().popBackStack()
        }.setScale(0.8f)
    }

    private fun initRv() {
        binding.rvEarn.layoutManager = LinearLayoutManager(context)
        binding.rvEarn.adapter = adapter
        adapter.submitList(DataManager.getListMission())
        adapter.onClickItem = { _, item ->
            if (MMKVUtils.getListPlayed().size > item.targetLevel) {
                val mList = MMKVUtils.getListEarned().toMutableList()
                if (mList.contains(item)){
                    context?.toast("Bạn đã nhận thưởng rồi")
                }else{
                    MMKVUtils.score += item.point
                    mList.add(item)
                    MMKVUtils.setListEarn(mList)
                    adapter.notifyDataSetChanged()
                    context?.toast("Nhận thưởng thành công")
                }
            }else{
                context?.toast("Vui lòng vượt qua màn ${item.targetLevel} để nhận thưởng")
            }
        }
    }

}