package com.example.officialmusicapp.viewmodel

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
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

@Suppress("DEPRECATION")
@HiltViewModel
class SongViewModel @Inject constructor(
    private val songRepository: SongRepository,
) : ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> get() = _songs

    private val _currentPlayingSong = MutableStateFlow<Song?>(null)
    val currentPlayingSong: StateFlow<Song?> get() = _currentPlayingSong

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> get() = _currentPosition

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> get() = _isPlaying

    private val _isShuffleEnabled = MutableStateFlow(false)
    val isShuffleEnabled: StateFlow<Boolean> get() = _isShuffleEnabled

    private var isTrackingPosition = false

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            songRepository.fetchAndSaveSongs()
            _songs.value = songRepository.getAllSongs().sortedByDescending { it.counter }
        }
    }

    fun startMusicService(context: Context, song: Song) {
        _currentPlayingSong.value = song
        _currentPosition.value = 0L

        val intent = Intent(context, MusicPlayerService::class.java).apply {
            putExtra("SONG_URL", song)
            putParcelableArrayListExtra("SONG_LIST", ArrayList(_songs.value))
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
                    _currentPosition.value = it.currentPosition
                }
            }
        }
    }

    fun toggleShuffle(context: Context){
        _isShuffleEnabled.value = !_isShuffleEnabled.value

        val intent = Intent("com.example.officialmusicapp.SHUFFLE_CHANGED")
        intent.putExtra("IS_SHUFFLE_ENABLED", _isShuffleEnabled.value)
        context.sendBroadcast(intent)
    }

    fun togglePlayPause(context: Context) {
        var action = ""
        if (_isPlaying.value) {
            action = "ACTION_PAUSE"
            _isPlaying.value = false
        } else {
            action = "ACTION_PLAY"
            _isPlaying.value = true
        }

        val intent = Intent(context, MusicPlayerService::class.java).apply {
            this.action = action
        }
        context.startService(intent)
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
        return if (_isShuffleEnabled.value) {
            _songs.value.randomOrNull()
        } else {
            _songs.value.getOrNull(currentIndex + 1) ?: _songs.value.getOrNull(0)
        }
    }

    private fun getPreviousSong(): Song? {
        val currentIndex = _songs.value.indexOf(_currentPlayingSong.value)
        return if (currentIndex > 0) {
            _songs.value[currentIndex - 1]
        } else {
            _songs.value.getOrNull(_songs.value.size - 1)
        }
    }

    fun seekTo(position: Long) {
        exoPlayer?.seekTo(position)
        _currentPosition.value = position
    }

    fun registerBroadcasts(context: Context) {
        val filter = IntentFilter().apply {
            addAction("com.example.officialmusicapp.SONG_CHANGED")
            addAction("com.example.officialmusicapp.PLAYBACK_STATE_CHANGED")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    when (intent?.action) {
                        "com.example.officialmusicapp.SONG_CHANGED" -> {
                            val song = intent.getParcelableExtra<Song>("NEW_SONG")
                            _currentPlayingSong.value = song
                            _currentPosition.value = 0L
                        }

                        "com.example.officialmusicapp.PLAYBACK_STATE_CHANGED" -> {
                            val isPlaying = intent.getBooleanExtra("IS_PLAYING", false)
                            _isPlaying.value = isPlaying
                        }
                    }
                }
            }, filter, Context.RECEIVER_NOT_EXPORTED)
        }
    }
}
