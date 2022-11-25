package com.example.usecases.di

import com.example.data.di.dataModules
import com.example.data.network.di.networkModule
import com.example.usecases.suggestedvideos.SuggestedVideosUsecase
import com.example.usecases.suggestedvideos.SuggestedVideosUsecaseImpl
import org.koin.dsl.module

val usecaseModule = module {
    factory<SuggestedVideosUsecase> {
        SuggestedVideosUsecaseImpl(
            searchResponseRepository = get()
        )
    }
}

val usecaseModules = dataModules + usecaseModule