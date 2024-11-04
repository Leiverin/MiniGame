package com.example.test

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.test.data.mmkv.MMKVUtils
import com.example.test.utils.exo_sound.ExoPlayer
import com.tencent.mmkv.MMKV

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleListener(this))

        MMKV.initialize(this)

        // Khởi tạo nhạc
        ExoPlayer.getInstance(this).initPlayer()
        ExoPlayer.getInstance(this).setMediaSource(Uri.parse("asset:///musics/bg_music.mp3"), MMKVUtils.isEnableMusic, true)
    }
}

class AppLifecycleListener(private val context: Context) : DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {

    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        ExoPlayer.getInstance(context).onPause()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        ExoPlayer.getInstance(context).onStart()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        ExoPlayer.getInstance(context).releaseExo()
    }
}