package com.example.domain.entities.search


data class SearchQuery(
    val queryParam: String?=null,
    val searchVideoPart: String?=null,
    val regCode: String?=null,
    val noOfResults: String?=null,
    val orderString: String?=null
)

