package com.example.domain.entities.search

data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: com.example.domain.entities.search.Thumbnails,
    val title: String
)