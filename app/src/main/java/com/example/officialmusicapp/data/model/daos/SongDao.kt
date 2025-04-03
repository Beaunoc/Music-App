package com.example.officialmusicapp.data.model.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.officialmusicapp.data.model.entities.Song

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSong(song: Song)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSongs(songs: List<Song>)

    @Query("SELECT * FROM songs")
    suspend fun getAllSongs(): List<Song>
}