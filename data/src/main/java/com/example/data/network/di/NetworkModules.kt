package com.example.data.network.di

import com.example.data.network.api.VideoApiService
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.internal.http2.Http2
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//import retrofit2.converter.
//implementation project(":data")
const val BASE_URL = "https://youtube-v31.p.rapidapi.com"
val interceptor = HttpLoggingInterceptor()
//interceptor.level = HttpLoggingInterceptor.Level.BODY
val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

val networkModule = module {

    single<VideoApiService> {
//        Retrofit.Builder()
//        get<Retrofit.Builder>(named("default"))
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideoApiService::class.java)
    }

}