package com.example.domain.entities.videodetail

data class Snippet(
    val categoryId: String,
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val localized: com.example.domain.entities.videodetail.Localized,
    val publishedAt: String,
    val tags: List<String>,
    val thumbnails: com.example.domain.entities.videodetail.Thumbnails,
    val title: String
)