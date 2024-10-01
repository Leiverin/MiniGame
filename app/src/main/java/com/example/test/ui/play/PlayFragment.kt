package com.example.test.ui.play

import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test.R
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.data.models.Level
import com.example.test.databinding.FragmentPlayBinding
import com.example.test.extension.loadImage
import com.example.test.extension.setBackPressListener
import com.example.test.extension.showDialogResult
import com.example.test.extension.toast
import com.example.test.ui.base.BaseFragment
import com.example.test.ui.play.adapters.AnswerAdapter
import com.example.test.ui.play.adapters.OfferAdapter
import com.example.test.utils.DataManager
import com.example.test.utils.pushdown.PushDownAnim

class PlayFragment: BaseFragment<FragmentPlayBinding>() {
    private var currentLevel: Level? = null
    private val answers = mutableListOf<String>()
    private val offers = mutableListOf<String>()
    private val offerAdapter = OfferAdapter()
    private val answerAdapter = AnswerAdapter()
    private val maps = mutableMapOf<Int, String>()
    private var hasResult = false

    override fun layoutRes(): Int = R.layout.fragment_play

    override fun initView() {
        binding.imgBg.loadImage(R.drawable.bg_home)
        initData()
        initEvents()
    }

    private fun initData() {
        getCurrentLevel()
        initRvOffer()
        initRvAnswer()
    }

    private fun initRvOffer() {
        binding.rvOffers.layoutManager = GridLayoutManager(context, 7)
        binding.rvOffers.adapter = offerAdapter

    }

    private fun initRvAnswer() {
        binding.rvAnswers.layoutManager = GridLayoutManager(context, answerAdapter.itemCount)
        binding.rvAnswers.adapter = answerAdapter
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
        offerAdapter.onClickItem = { pos, offer ->
            val index = answers.indexOfFirst { it == "" }
            if (index >= 0) {
                answers[index] = offer
                offers[pos] = ""
                maps[pos] = offer
                refresh()
            }
            if (answers.indexOfFirst { it == "" } < 0 && !hasResult){
                val result = answers.joinToString("")
                if (result == currentLevel?.answers?.joinToString("")){
                    handleWin(result)
                }else{
                    handleLose()
                }
            }

        }
        answerAdapter.onClickItem = { pos, answer ->
            hasResult = false
            var count = answers.count { it == answer }
            if (count <= 1){
                val item = maps.entries.find { it.value == answer }
                item?.let {
                    offers[item.key] = item.value
                }
            }else{
                for ((k, v) in maps) {
                    if (v == answer) count--
                    if (count == 0) {
                        offers[k] = v
                        break
                    }
                }
            }
            answers[pos] = ""
            refresh()
        }
    }

    private fun handleLose() {
        getCurrentLevel()
        context?.toast("Đáp án sai")
    }

    private fun handleWin(result: String) {
        hasResult = true
        context?.showDialogResult(result)
        val list = MMKVUtils.getListPlayed().toMutableList()
        currentLevel?.let {
            list.add(it)
            MMKVUtils.setListPlayed(list)
            getCurrentLevel()
        }
    }

    private fun refresh(){
        answerAdapter.submitList(answers)
        offerAdapter.submitList(offers)
    }

    private fun getCurrentLevel(){
        binding.tvLevelCurrent.text = MMKVUtils.getListPlayed().size.toString()
        binding.tvScore.text = (MMKVUtils.getListPlayed().size * 12).toString()
        hasResult = false
        val levels = mutableListOf<Level>()
        levels.clear()
        answers.clear()
        offers.clear()
        levels.addAll(DataManager.getInstance().getListAssets(null))
        levels.removeAll { level1 -> MMKVUtils.getListPlayed().any { level2 -> level1.imgOffer == level2.imgOffer } }
        currentLevel = levels.first()
            currentLevel?.let { level ->
            binding.imgGame.loadImage(level.imgOffer)
            offerAdapter.submitList(level.offers)
            answers.addAll(List(level.answers.size){ "" })
            offers.addAll(level.offers)
            answerAdapter.submitList(answers)
        }
    }

}