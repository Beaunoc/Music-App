package com.example.officialmusicapp.data.model.entities

data class ApiSong(
    val id: String,
    val title: String,
    val album: String,
    val artist: String,
    val source: String,
    val image: String,
    val duration: Int,
    val favorite: String,
    val counter: Int,
    val replay: Int,
)
