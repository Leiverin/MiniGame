package com.example.test.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.toast(int: Int, length: Int = Toast.LENGTH_SHORT){
    toast(getString(int), length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    doToast(this@toast, msg, length)
}

private fun doToast(context: Context, msg: String, length: Int) {
    CoroutineScope(Dispatchers.Main).launch {
        if (context is Activity) {
            if (!context.isDestroyed && !context.isFinishing) {
                Toast.makeText(context, msg, length).show()
            }
        } else {
            Toast.makeText(context, msg, length).show()
        }
    }
}