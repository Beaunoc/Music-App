package com.example.officialmusicapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.officialmusicapp.data.model.daos.SongDao
import com.example.officialmusicapp.data.model.entities.Song

@Database(entities = [Song::class], version = 3)
abstract class MusicDatabase: RoomDatabase() {
    abstract fun songDao(): SongDao
}