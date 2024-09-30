package com.example.test.extension

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.beGone(){
    this.visibility = View.GONE
}

fun View.beInvisible(){
    this.visibility = View.INVISIBLE
}

fun View.beVisible(){
    this.visibility = View.VISIBLE
}

fun ImageView.loadImage(url: String){
    Glide.with(this).load(url).into(this)
}

fun ImageView.loadImage(resId: Int){
    Glide.with(this).load(resId).into(this)
}

fun View.setPreventDoubleClick(debounceTime: Long = 300, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0
        override fun onClick(v: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
