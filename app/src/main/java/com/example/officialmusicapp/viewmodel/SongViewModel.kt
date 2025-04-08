package com.example.officialmusicapp.viewmodel

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officialmusicapp.data.model.entities.Song
import com.example.officialmusicapp.data.repository.SongRepository
import com.example.officialmusicapp.service.MusicPlayerService
import com.example.officialmusicapp.service.MusicPlayerService.Companion.exoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> get() = _songs

    private val _currentPlayingSong = MutableStateFlow<Song?>(null)
    val currentPlayingSong: StateFlow<Song?> get() = _currentPlayingSong

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> get() = _currentPosition

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> get() = _isPlaying

    private var isTrackingPosition = false

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            _songs.value = songRepository.getAllSongs()
        }
    }

    fun startMusicService(context: Context, song: Song) {
        _currentPlayingSong.value = song
        val intent = Intent(context, MusicPlayerService::class.java).apply {
            putExtra("SONG_URL", song.source)
        }
        context.startService(intent)
        _isPlaying.value = true
        startTrackingPosition()
    }

    private fun startTrackingPosition() {
        if (isTrackingPosition) return
        isTrackingPosition = true
        viewModelScope.launch {
            while (true) {
                delay(1000)
                exoPlayer?.let {
                    if (it.isPlaying) {
                        _currentPosition.value = it.currentPosition
                    }
                }
            }
        }
    }

    fun togglePlayPause() {
        exoPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                _isPlaying.value = false
            } else {
                it.play()
                _isPlaying.value = true
            }
        }
    }

    fun playNextSong(context: Context) {
        getNextSong()?.let {
            startMusicService(context, it)
        }
    }

    fun playPreviousSong(context: Context) {
        getPreviousSong()?.let {
            startMusicService(context, it)
        }
    }

    private fun getNextSong(): Song? {
        val currentIndex = _songs.value.indexOf(_currentPlayingSong.value)
        return _songs.value.getOrNull(currentIndex + 1)
    }

    private fun getPreviousSong(): Song? {
        val currentIndex = _songs.value.indexOf(_currentPlayingSong.value)
        return if (currentIndex > 0) _songs.value[currentIndex - 1] else null
    }

    fun seekTo(position: Long) {
        exoPlayer?.seekTo(position)
        _currentPosition.value = position
    }
}
