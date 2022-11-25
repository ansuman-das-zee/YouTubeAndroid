package com.example.domain.entities.suggested

data class Item(
    val id: com.example.domain.entities.suggested.Id,
    val kind: String,
    val snippet: com.example.domain.entities.suggested.Snippet
)