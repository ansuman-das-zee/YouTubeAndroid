package com.example.youtubeandroid.di

import com.example.usecases.di.usecaseModules
import com.example.youtubeandroid.viewModel.ScrollScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        ScrollScreenViewModel(
            sugVideos = get()
        )
    }
}

val appModule = usecaseModules + presentationModule