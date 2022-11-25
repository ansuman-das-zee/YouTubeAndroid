package com.example.domain.entities.search

data class Item(
    val id: com.example.domain.entities.search.Id,
    val kind: String,
    val snippet: com.example.domain.entities.search.Snippet
)