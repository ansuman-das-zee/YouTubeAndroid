package com.example.presentation.player

//import com.google.android.exoplayer2.ExoPlayer

interface Player {

    fun initializePlayer()
    fun onStart()
    fun onResume()
    fun hideSystemUi()
    fun onPause()
    fun onStop()
    fun releasePlayer()


//    private var player: ExoPlayer? = null
//    private var playWhenReady = true
//    private var currentItem = 0
//    private var playbackPosition = 0L
}