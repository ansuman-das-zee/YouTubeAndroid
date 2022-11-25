package com.example.youtubeandroid.player

import android.annotation.SuppressLint
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import com.example.youtubeandroid.databinding.ActivityPlayerBinding

//abstract class PlayerImpl : Player,AppCompatActivity() {

class PlayerImpl : Player,AppCompatActivity() {



    private lateinit var player: ExoPlayer
//    private var testPlayer : ExoPlayer.Builder? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private lateinit var urlData : String

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityPlayerBinding.inflate(layoutInflater)
    }

    //oncreate

    override fun initializePlayer(): ExoPlayer? {

        Log.e("initializePlayer","urlData = "+urlData)

//        thisPlayr = ExoPlayer.Builder(this)


        player = ExoPlayer.Builder(this
        )
            .build()
            .also{exoPlayer ->
                viewBinding.videoView.player = exoPlayer
                Log.e("PlayerBuilder","Player has been build")
//                val mediaItem = androidx.media3.common.MediaItem.fromUri(getString(R.string.media_url_mp3))
//                getUrlData()
                Log.e("PlayerBuilder","Player has been build and media Item retrieved")
//                val mediaItem = androidx.media3.common.MediaItem.fromUri("https://www.youtube.com/watch?v=VaLO-KWNHyc")
                val mediaItem = androidx.media3.common.MediaItem.fromUri("https://www.youtube.com/watch?v=VaLO-KWNHyc")

                Log.e("MediaItem ","MediaItem worked")


                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem,playbackPosition)
                exoPlayer.prepare()


            }

        return player
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    @SuppressLint("InlinedApi")
    override fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, viewBinding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }



    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    override fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    override fun setUrlData(urlDataInp : String){
        urlData = urlDataInp
        Log.e("setUrlData1","set Data to "+urlData)

    }



}