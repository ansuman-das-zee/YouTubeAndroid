package com.example.usecases.suggestedvideos

import com.example.domain.entities.search.SearchResponseDataClass
import com.example.usecases.base.BaseUsecase


interface SuggestedVideosUsecase : BaseUsecase<SuggestedVideosUsecase.Input,Result<SearchResponseDataClass>>{
    data class Input(var queryParam : String?=null,
                     var searchVideoPart:String?=null,
                     var regCode: String?=null,
                     var noOfResults: String?=null,
                     var orderString: String?=null)


}