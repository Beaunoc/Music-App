package com.example.officialmusicapp.data.api

import com.example.officialmusicapp.data.model.entities.Song
import com.example.officialmusicapp.data.model.entities.SongResponse
import retrofit2.http.GET

interface MusicApiService {
    @GET("resources/braniumapis/songs.json")
    suspend fun fetchSongs(): SongResponse
}