package com.example.data.repositoriesimpl


import com.example.data.network.api.VideoApiService
import com.example.domain.entities.search.PageInfo
import com.example.domain.entities.search.SearchResponseDataClass
import com.example.domain.entities.search.SearchQuery
import com.example.domain.repositories.SearchResponseRepository
//import kotlinx.serialization.json.Json

class SearchResponseRepositoryImpl(

    private val apiService: VideoApiService
): SearchResponseRepository {

    override suspend fun getSuggestedVideos(searchQuery: SearchQuery
    ): Result<SearchResponseDataClass> {

        return runCatching {
            //Result.success(
//            val temp : String = apiService.getSuggestedVideos("New", "snippet", "US", "20", "date").body().toString()
//            Log.e("Repository",temp)



//            apiService.getSuggestedVideos(searchQuery.queryParam.toString(), searchQuery.searchVideoPart.toString(), searchQuery.regCode.toString(), searchQuery.noOfResults.toString(), searchQuery.orderString.toString()).body()
//                ?: SearchResponseDataClass(listOf(), "", "", PageInfo(0, 0), "")
//            val a = searchQuery.searchVideoPart
//            println(a)
//
//            val b = searchQuery.queryParam
//            println(b)
//
//            val c=searchQuery.regCode
//            println(c)
//
//            val d = searchQuery.orderString
//            println(d)
//
//            val e = searchQuery.noOfResults
//            println(e)


            apiService.getSuggestedVideos(searchQuery.queryParam.toString(), searchQuery.searchVideoPart.toString(), searchQuery.regCode.toString(), searchQuery.noOfResults.toString(), searchQuery.orderString.toString()).body()
                ?: SearchResponseDataClass(listOf(), "", "", PageInfo(0, 0), "")

//            apiService.getSuggestedVideos("New", "snippet,id", "US", "20", "date").body()
//                ?: SearchResponseDataClass(listOf(), "", "", PageInfo(0, 0), "")

//            apiService.getSuggestedVideos("ae522d915bmshe4b00ec8cc04285p1ae62djsne30cd27edead","youtube-v31.p.rapidapi.com","New", "snippet,id", "US", "20", "date").body()
//                ?: SearchResponseDataClass(listOf(), "", "", PageInfo(0, 0), "")
            /*).map { response ->
                SearchResponseDataClass(
                    items = response.items.map { item ->
                        Item(
                            id = item.id,
                            kind = item.kind,
                            snippet = item.snippet
                        )

                    },
                    kind = response.kind,
                    nextPageToken = response.nextPageToken,
                    pageInfo = response.pageInfo,
                    regionCode = response.regionCode
                )
            }*/



        }.onFailure {
            Result.failure<SearchResponseDataClass>(it)
        }

//        return Result.success(
//            apiService.getSuggestedVideos("New", "snippet", "US", "20", "date").body() ?:
//            SearchResponseDataClass(listOf(),"","", PageInfo(0,0),"")
//        )

        /*.map{ response ->
                SearchResponseDataClass(
                    items = response.items.map{item->
                        SearchResponseDataClass.(
                            id = item.id,
                            kind = item.kind,
                            snippet = item.kind
                        )


                    },
                    kind = response.kind,
                    nextPageToken = response.nextPageToken,
                    pageInfo = response.pageInfo,
                    regionCode = response.egionCode
                )*/

    }
}



//private fun <T> Any.failure() {
//    TOD("Not yet implemented")
//    return null
//}