package com.example.test.ui.home

import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.extension.loadImage
import com.example.test.extension.setBackPressListener
import com.example.test.extension.showDialogIntroduction
import com.example.test.ui.base.BaseFragment
import com.example.test.utils.Constants
import com.example.test.utils.exo_sound.ExoPlayer
import com.example.test.utils.pushdown.PushDownAnim

class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    override fun layoutRes(): Int = R.layout.fragment_home

    override fun initView() {
        binding.imgHome.loadImage(R.drawable.bg_home)
        initData()
        initEvents()
    }

    private fun initData() {
        // Lay thong tin cac levels da duoc choi
        binding.tvLevelCurrent.text = MMKVUtils.getListPlayed().size.toString()

        context?.let {
            ExoPlayer.getInstance(it).onStateChanged = { isPlaying ->
                if(isPlaying){
                    binding.btnSound.setImageResource(R.drawable.ic_sound_on)
                }else{
                    binding.btnSound.setImageResource(R.drawable.ic_sound_off)
                }
            }
            if(ExoPlayer.getInstance(it).isPlaying()){
                binding.btnSound.setImageResource(R.drawable.ic_sound_on)
            }else{
                binding.btnSound.setImageResource(R.drawable.ic_sound_off)
            }
        }
    }

    private fun initEvents() {
        setBackPressListener{}

        // Xử lý sự kiện của nút "chơi ngay"
        PushDownAnim.setPushDownAnimTo(binding.btnPlay).setOnClickListener {
            navToPlay()
        }.setScale(0.8f)

        // Xử lý sự kiện của nút "kiếm ruby"
        PushDownAnim.setPushDownAnimTo(binding.btnAddCoin).setOnClickListener {
            navToEarnRuby()
        }.setScale(0.8f)

        // Xử lý sự kiện của nút âm thanh
        PushDownAnim.setPushDownAnimTo(binding.btnSound).setOnClickListener {
            context?.let {
                if (!ExoPlayer.getInstance(it).isPlaying()){
                    MMKVUtils.isEnableMusic = true
                    ExoPlayer.getInstance(it).onStart()
                }else{
                    MMKVUtils.isEnableMusic = false
                    ExoPlayer.getInstance(it).onPause()
                }
            }
        }.setScale(0.8f)

        // Xử lý sự kiện của nút thông tin
        PushDownAnim.setPushDownAnimTo(binding.btnInfo).setOnClickListener {
            context?.showDialogIntroduction()
        }.setScale(0.8f)

        // Xử lý sự kiện của nút email
        PushDownAnim.setPushDownAnimTo(binding.btnEmail).setOnClickListener {
            context?.let{ context ->
                val title = "Góp ý cho chúng tôi"
                Constants.sendEmailMoree(context, arrayOf(EMAIL_FEEDBACK), title, "")
            }
        }.setScale(0.8f)

//        PushDownAnim.setPushDownAnimTo(binding.btnUser).setOnClickListener {
//        }.setScale(0.8f)
    }

    private fun navToPlay(){
        val directions = HomeFragmentDirections.actionHomeFragmentToPlayFragment()
        findNavController().navigate(directions)
    }

    private fun navToEarnRuby(){
        val directions = HomeFragmentDirections.actionHomeFragmentToMakeRubyFragment()
        findNavController().navigate(directions)
    }

    companion object{
        const val EMAIL_FEEDBACK = "ducchienkma@gmail.com"
    }
}