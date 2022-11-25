package com.example.domain.entities.videodetail

data class ContentDetails(
    val caption: String,
    val contentRating: com.example.domain.entities.videodetail.ContentRating,
    val definition: String,
    val dimension: String,
    val duration: String,
    val licensedContent: Boolean,
    val projection: String,
    val regionRestriction: com.example.domain.entities.videodetail.RegionRestriction
)