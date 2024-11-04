package com.example.test.data.mmkv

import com.example.test.data.models.Level
import com.example.test.data.models.RubyModel
import com.example.test.extension.decodeListParcelable
import com.example.test.extension.encodeListParcelable
import com.tencent.mmkv.MMKV

object MMKVUtils {

    // Lưu trạng thái enable/disable music
    var isEnableMusic: Boolean
        get() = MMKV.defaultMMKV().decodeBool(MMKVKey.ENABLE_MUSIC, true)
        set(value) {
            MMKV.defaultMMKV().encode(MMKVKey.ENABLE_MUSIC, value)
        }
    // Điểm
    var score: Int
        get() = MMKV.defaultMMKV().decodeInt(MMKVKey.SCORE, 0)
        set(value) {
            MMKV.defaultMMKV().encode(MMKVKey.SCORE, value)
        }

    // Lấy ra những màn đã chơi
    fun getListPlayed(): List<Level>{
        return MMKV.defaultMMKV().decodeListParcelable(MMKVKey.LIST_PLAYED, listOf()) ?: emptyList()
    }

    // Lưu những màn đã chơi
    fun setListPlayed(level: List<Level>){
        MMKV.defaultMMKV().encodeListParcelable(MMKVKey.LIST_PLAYED, level)
    }

    // Lấy ra những ruby đã thắng
    fun getListEarned(): List<RubyModel>{
        return MMKV.defaultMMKV().decodeListParcelable(MMKVKey.LIST_EARNED, listOf()) ?: emptyList()
    }

    // Lưu những nhiệm vụ đã nhận
    fun setListEarn(level: List<RubyModel>){
        MMKV.defaultMMKV().encodeListParcelable(MMKVKey.LIST_EARNED, level)
    }

}