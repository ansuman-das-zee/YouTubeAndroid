package com.example.domain.entities.videodetail

data class Thumbnails(
    val default: com.example.domain.entities.videodetail.Default,
    val high: com.example.domain.entities.videodetail.High,
    val maxres: com.example.domain.entities.videodetail.Maxres,
    val medium: com.example.domain.entities.videodetail.Medium,
    val standard: com.example.domain.entities.videodetail.Standard
)