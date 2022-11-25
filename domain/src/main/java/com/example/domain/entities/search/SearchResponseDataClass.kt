package com.example.domain.entities.search

data class SearchResponseDataClass(
    val items: List<com.example.domain.entities.search.Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: com.example.domain.entities.search.PageInfo,
    val regionCode: String
)