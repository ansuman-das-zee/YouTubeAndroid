package com.example.youtubeandroid.player

import androidx.media3.exoplayer.ExoPlayer

//import com.google.android.exoplayer2.ExoPlayer

interface Player {

    fun initializePlayer(): ExoPlayer?
    fun onStart()
    fun onResume()
    fun hideSystemUi()
    fun onPause()
    fun onStop()
    fun releasePlayer()
    fun setUrlData(urlDataInp : String)


//    private var player: ExoPlayer? = null
//    private var playWhenReady = true
//    private var currentItem = 0
//    private var playbackPosition = 0L
}