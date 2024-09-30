package com.example.test.extension

import android.os.SystemClock
import android.view.View

fun View.beGone(){
    this.visibility = View.GONE
}

fun View.beInvisible(){
    this.visibility = View.INVISIBLE
}

fun View.beVisible(){
    this.visibility = View.VISIBLE
}

fun View.setPreventDoubleClick(debounceTime: Long, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
