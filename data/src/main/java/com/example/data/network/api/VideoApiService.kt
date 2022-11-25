package com.example.data.network.api

import com.example.domain.entities.search.SearchResponseDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.domain.entities.videodetail.VideoDetailDataClass
import retrofit2.http.Headers

interface VideoApiService {
//    /videos?part=contentDetails%2Csnippet%2Cstatistics&id={id}
    //suspendexecuts this function in a coroutine to prevent it runnning it in the main thread
    @GET("/videos")
    suspend fun getVideo(
    @Query("part") VideoPart: String,
    @Query("id") videoId : String
    ): Response<VideoDetailDataClass>

//    search?relatedToVideoId=7ghhRHRP6t4&part=id%2Csnippet&type=video&maxResults=50"
   @GET("/search")
   @Headers(
       "X-RapidAPI-Key:ae522d915bmshe4b00ec8cc04285p1ae62djsne30cd27edead",
       "X-RapidAPI-Host:youtube-v31.p.rapidapi.com",
//
   )
   suspend fun getSuggestedVideos(
//    @Header("X-RapidAPI-Host")header1:String,
//    @Header("X-RapidAPI-Key")header2:String,
    @Query("q") queryParam : String,
    @Query("part") searchVideoPart:String,
    @Query("regionCode") regCode: String,
    @Query("maxResults") noOfResults: String,
    @Query("order") orderString: String

): Response<SearchResponseDataClass>


}