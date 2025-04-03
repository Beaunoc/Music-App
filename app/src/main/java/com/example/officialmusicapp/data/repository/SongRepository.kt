package com.example.officialmusicapp.data.repository

import android.util.Log
import com.example.officialmusicapp.data.model.daos.SongDao
import com.example.officialmusicapp.data.model.entities.Song
import com.example.officialmusicapp.utils.RetrofitInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepository @Inject constructor(
    private val songDao: SongDao,
) {
    suspend fun fetchAndSaveSongs() {
        val response = RetrofitInstance.api.fetchSongs()

        val songs = response.songs
        Log.d("SongRepository", "Fetched songs from API: $songs")
        songDao.insertAllSongs(songs)
    }

    suspend fun getAllSongs(): List<Song> {
        val allSongs = songDao.getAllSongs()
        Log.d("SongRepository", "Retrieved songs from Room: $allSongs")
        return allSongs
    }
}