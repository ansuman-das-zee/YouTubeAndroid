package com.example.domain.entities.videodetail

data class VideoDetailDataClass(
    val items: List<com.example.domain.entities.videodetail.Item>,
    val kind: String,
    val pageInfo: com.example.domain.entities.videodetail.PageInfo
)