package com.example.test.utils.exo_sound

import android.net.Uri
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView

interface ExoPlayerController {

    fun initPlayer()

    fun onStart()

    fun onStop()

    fun onPause()

    fun onNext()

    fun onPrevious()

    fun isPlaying(): Boolean

    fun setVolume(volume: Float)

    fun getVolume(): Float

    fun releaseExo()

    fun maxDurationCurrent(): Long

    fun setPlayerView(playerView: PlayerView)

    fun addPlayerCallback(callback: Player.Listener)

    fun currentDuration(): Long

    fun seekTo(duration: Long)

    fun setMediaSource(path: String, isPlayWhenReady: Boolean, isRepeat: Boolean)

    fun setMediaSource(uri: Uri, isPlayWhenReady: Boolean, isRepeat: Boolean)

}