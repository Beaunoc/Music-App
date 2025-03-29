package com.example.officialmusicapp.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey val id: Long,
    val title: String,
    val album: String,
    val artist: String,
    val sourceMp3: String,
    val imageUrl: String,
    val duration: Int,
    val favourite: Boolean,
)