package com.example.domain.entities.videodetail

data class Item(
    val contentDetails: com.example.domain.entities.videodetail.ContentDetails,
    val id: String,
    val kind: String,
    val snippet: com.example.domain.entities.videodetail.Snippet,
    val statistics: com.example.domain.entities.videodetail.Statistics
)