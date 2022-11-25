package com.example.domain.entities.suggested

data class Thumbnails(
    val default: com.example.domain.entities.suggested.Default,
    val high: com.example.domain.entities.suggested.High,
    val maxres: com.example.domain.entities.suggested.Maxres,
    val medium: com.example.domain.entities.suggested.Medium,
    val standard: com.example.domain.entities.suggested.Standard
)