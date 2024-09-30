package com.example.test.data.mmkv

import com.example.test.data.models.Level
import com.example.test.extension.decodeListParcelable
import com.example.test.extension.encodeListParcelable
import com.tencent.mmkv.MMKV

object MMKVUtils {

    var isFirstOpen: Boolean
        get() = MMKV.defaultMMKV().decodeBool(MMKVKey.IS_FIRST_OPEN, true)
        set(value) {
            MMKV.defaultMMKV().encode(MMKVKey.IS_FIRST_OPEN, value)
        }

    fun getListPlayed(): List<Level>{
        return MMKV.defaultMMKV().decodeListParcelable(MMKVKey.LIST_PLAYED, listOf()) ?: emptyList()
    }
    fun setListPlayed(level: List<Level>){
        MMKV.defaultMMKV().encodeListParcelable(MMKVKey.LIST_PLAYED, level)
    }

}