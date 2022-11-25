package com.example.data.di

import com.example.data.network.di.networkModule
import com.example.data.repositoriesimpl.SearchResponseRepositoryImpl
import com.example.domain.repositories.SearchResponseRepository
import org.koin.dsl.module

val dataModule = module {
    factory<SearchResponseRepository>{
        SearchResponseRepositoryImpl(
            apiService = get()
        )
    }
}

val dataModules = dataModule + networkModule