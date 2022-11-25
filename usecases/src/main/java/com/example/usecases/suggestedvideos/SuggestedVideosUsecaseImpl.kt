package com.example.usecases.suggestedvideos

import com.example.domain.entities.search.SearchResponseDataClass
import com.example.domain.entities.search.SearchQuery
import com.example.domain.repositories.SearchResponseRepository


internal class SuggestedVideosUsecaseImpl(private val searchResponseRepository: SearchResponseRepository)
    :SuggestedVideosUsecase
{
    override suspend fun exec(input: SuggestedVideosUsecase.Input): Result<SearchResponseDataClass> {
//        TOO("Not yet implemented"
        return searchResponseRepository.getSuggestedVideos(
            SearchQuery(
                input.queryParam,
                input.searchVideoPart,
                input.regCode,
                input.noOfResults,
                input.orderString

            )

        )
    }
}
