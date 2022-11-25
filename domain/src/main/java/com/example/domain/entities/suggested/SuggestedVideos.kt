package com.example.domain.entities.suggested

data class SuggestedVideos(
    val items: List<com.example.domain.entities.suggested.Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: com.example.domain.entities.suggested.PageInfo,
    val regionCode: String
)