package com.example.youtubeandroid.event

import com.example.domain.entities.search.Id

sealed class Event {
    data class playButtonClick(val id: Id) : Event()
    object backButtonClick : Event()
}