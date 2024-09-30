package com.example.test.data.mmkv

import com.tencent.mmkv.MMKV

object MMKVUtils {

    var isFirstOpen: Boolean
        get() = MMKV.defaultMMKV().decodeBool(MMKVKey.IS_FIRST_OPEN, true)
        set(value) {
            MMKV.defaultMMKV().encode(MMKVKey.IS_FIRST_OPEN, value)
        }

}