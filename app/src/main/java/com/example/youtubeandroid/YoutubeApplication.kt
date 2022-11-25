package com.example.youtubeandroid

import android.app.Application
import com.example.youtubeandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class YoutubeApplication : Application(){
    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidLogger()
            androidContext(this@YoutubeApplication)
            modules(appModule)
        }
    }
}