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

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            _songs.value = songRepository.getAllSongs()
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    fun startPositionReceiver(context: Context) {
        val filter = IntentFilter("com.example.officialmusicapp.ACTION_UPDATE_POSITION")
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val currentPosition = intent?.getLongExtra("currentPosition", 0L) ?: 0L
                _currentPosition.value = currentPosition
            }
        }
        viewModelScope.launch {
            context.registerReceiver(receiver, filter)
        }
    }

    fun startMusicService(context: Context, song: Song) {
        _currentPlayingSong.value = song
        val intent = Intent(context, MusicPlayerService::class.java).apply {
            putExtra("SONG_URL", song.source)
        }
        context.startService(intent)
        _isPlaying.value = true
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

    fun updateCurrentPosition(newPosition: Long) {
        _currentPosition.value = newPosition
    }
}
