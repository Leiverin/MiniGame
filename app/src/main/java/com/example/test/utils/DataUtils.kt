package com.example.test.utils

import android.content.Context

object DataUtils {

    fun getListAssets(context: Context): MutableList<String>{
        val assets = context.resources.assets.list("levels")
        return assets?.toMutableList() ?: mutableListOf()
    }

}