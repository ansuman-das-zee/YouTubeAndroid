package com.example.domain.repositories

import com.example.domain.entities.search.SearchResponseDataClass
import com.example.domain.entities.search.SearchQuery

//interface searchResponseRepository {
//    suspend fun getSuggestedVideos(SearchQuery(
//    input.queryParam,
//    input.searchVideoPar,
//    input.regCode,
//    input.noOfResults,
//    input.orderString
//    )): Result<SearchResponseDataClass>
//}

interface SearchResponseRepository {
    suspend fun getSuggestedVideos(searchquery: SearchQuery): Result<SearchResponseDataClass>
}