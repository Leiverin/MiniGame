package com.example.test.utils.exo_sound

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.ui.PlayerView

@OptIn(UnstableApi::class)
class ExoPlayer(
    private val context: Context,
) : ExoPlayerController, Player.Listener {
    private var player: androidx.media3.exoplayer.ExoPlayer? = null
    private var maxDuration = 0L

    var onStateChanged: (Boolean) -> Unit = {}
    var onProgressChanged: (Long) -> Unit = {}

    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            player?.let {
                if (maxDuration > 0L && it.currentPosition > 0L) {
                    onProgressChanged.invoke(it.currentPosition)
                }
            }
            handler.postDelayed(this, 200)
        }
    }

    override fun initPlayer() {
        player = androidx.media3.exoplayer.ExoPlayer
            .Builder(context)
            .setRenderersFactory(DefaultRenderersFactory(context).setEnableDecoderFallback(true))
            .build()
        player?.addListener(this)
    }

    override fun onStart() {
        player?.play()
    }

    override fun onStop() {
        player?.stop()
    }

    override fun onPause() {
        player?.pause()
    }

    override fun onNext() {
    }

    override fun onPrevious() {

    }

    override fun isPlaying(): Boolean = player?.isPlaying ?: false

    override fun setVolume(volume: Float) {
        player?.volume = volume
    }

    override fun getVolume(): Float = player?.volume ?: 0f

    override fun releaseExo() {
        player?.stop()
        player?.release()
        player = null
    }

    override fun maxDurationCurrent(): Long {
        return player?.duration ?: 0L
    }

    override fun setPlayerView(playerView: PlayerView) {
        playerView.player = player
    }

    override fun addPlayerCallback(callback: Player.Listener) {
        player?.addListener(callback)
    }

    override fun currentDuration(): Long = player?.currentPosition ?: 0L

    override fun seekTo(duration: Long) {
        player?.seekTo(duration)
    }

    override fun setMediaSource(path: String, isPlayWhenReady: Boolean, isRepeat: Boolean) {
        val mediaItem = MediaItem.fromUri(path)
        player?.setMediaItem(mediaItem)
        player?.playWhenReady = isPlayWhenReady
        if (isRepeat) player?.repeatMode = Player.REPEAT_MODE_ONE
        else player?.repeatMode = Player.REPEAT_MODE_OFF
        player?.prepare()
    }

    override fun setMediaSource(uri: Uri, isPlayWhenReady: Boolean, isRepeat: Boolean) {
        val mediaItem = MediaItem.fromUri(uri)
        player?.setMediaItem(mediaItem)
        player?.playWhenReady = isPlayWhenReady
        if (isRepeat) player?.repeatMode = Player.REPEAT_MODE_ONE
        else player?.repeatMode = Player.REPEAT_MODE_OFF
        player?.prepare()
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            Player.STATE_READY -> {
                maxDuration = player?.duration ?: 0L
            }

            else -> {}
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        onStateChanged.invoke(isPlaying)
        if (isPlaying) {
            handler.post(runnable)
        } else {
            handler.removeCallbacks(runnable)
        }
    }


    companion object{
        var instance: ExoPlayer? = null

        fun getInstance(context: Context): ExoPlayer{
            if (instance == null){
                instance = ExoPlayer(context)
            }
            return instance!!
        }

    }
}