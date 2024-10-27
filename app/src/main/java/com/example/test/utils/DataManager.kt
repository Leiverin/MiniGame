package com.example.test.utils

import android.content.Context
import com.example.test.data.models.Level
import com.example.test.data.models.RubyModel
import com.example.test.extension.removeMimeType
import com.example.test.extension.removeSpace

class DataManager {

    private val levels = mutableListOf<Level>()

    fun getListAssets(context: Context?): MutableList<Level>{
        if (levels.isNotEmpty()){
            return levels
        }
        val assets = context?.resources?.assets?.list("levels")
        assets?.forEachIndexed { _,  s->
            levels.add(Level(
                imgOffer = Constants.ASSET_DIRECTORY + s,
                answers = s.removeSpace().removeMimeType().toCharArray().map { it.toString() }.toMutableList(),
                offers = getListOffers(s.removeSpace().removeMimeType()).toCharArray().map { it.toString() }.toMutableList()
            ))
        }
        return levels
    }

    private fun getListOffers(answer: String): MutableList<Char>{
        return (answer.toList() + characters.shuffled().take(14 - answer.toList().size)).shuffled().toMutableList()
    }

    private val characters = ('A'..'Z').map { it }

    companion object{

        private var instance: DataManager? = null

        fun getInstance(): DataManager{
            if (instance == null){
                instance = DataManager()
            }
            return instance!!
        }

        fun getListMission() = arrayListOf(
            RubyModel(1, 3, 12),
            RubyModel(2, 6, 12),
            RubyModel(3, 12, 12),
            RubyModel(4, 15, 12),
            RubyModel(5, 18, 12),
            RubyModel(6, 22, 12),
            RubyModel(7, 26, 12),
            RubyModel(8, 32, 12),
        )

    }

}