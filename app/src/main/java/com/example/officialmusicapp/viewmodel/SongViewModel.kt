package com.example.officialmusicapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officialmusicapp.data.model.entities.Song
import com.example.officialmusicapp.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val songRepository: SongRepository,
) : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> get() = _songs

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            try {
                val songsFromApi = songRepository.fetchAndSaveSongs()
                Log.d("SongViewModel", "Fetched songs: $songsFromApi")  // Log kết quả
                _songs.value = songRepository.getAllSongs()

            } catch (e: Exception){
                Log.e("SongViewModel", "Error fetching songs", e)
                e.printStackTrace()
            }
        }
    }
}