package com.example.officialmusicapp.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.media.app.NotificationCompat.MediaStyle
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.officialmusicapp.R
import com.example.officialmusicapp.data.model.entities.Song
import kotlinx.coroutines.*

@Suppress("DEPRECATION")
class MusicPlayerService : Service() {
    companion object {
        var exoPlayer: ExoPlayer? = null
    }

    private lateinit var mediaSession: MediaSessionCompat
    private var songList: List<Song> = listOf()
    private var currentIndex = 0
    private var currentSong: Song? = null

    private val serviceScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(this).build()
        }

        mediaSession = MediaSessionCompat(this, "MusicPlayerService").apply {
            setCallback(object : MediaSessionCompat.Callback() {
                override fun onPlay() {
                    exoPlayer?.play()
                    updatePlaybackState(true)
                    updateNotification()
                }

                override fun onPause() {
                    exoPlayer?.pause()
                    updatePlaybackState(false)
                    updateNotification()
                }

                override fun onSkipToNext() {
                    playSongAt(currentIndex + 1)
                }

                override fun onSkipToPrevious() {
                    playSongAt(currentIndex - 1)
                }

                override fun onSeekTo(pos: Long) {
                    exoPlayer?.seekTo(pos)
                }
            })
            isActive = true
        }

        exoPlayer?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) playSongAt(currentIndex + 1)
                updateNotification()
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlaybackState(isPlaying)
                updateNotification()
                sendBroadcast(Intent("com.example.officialmusicapp.PLAYBACK_STATE_CHANGED").apply {
                    putExtra("IS_PLAYING", isPlaying)
                })
            }
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "ACTION_PLAY" -> {
                exoPlayer?.play()
                updatePlaybackState(true)
                updateNotification()
                sendBroadcast(Intent("com.example.officialmusicapp.PLAYBACK_STATE_CHANGED").apply {
                    putExtra("IS_PLAYING", true)
                })
            }
            "ACTION_PAUSE" -> {
                exoPlayer?.pause()
                updatePlaybackState(false)
                updateNotification()
                sendBroadcast(Intent("com.example.officialmusicapp.PLAYBACK_STATE_CHANGED").apply {
                    putExtra("IS_PLAYING", false)
                })
            }
            "ACTION_NEXT" -> playSongAt(currentIndex + 1)
            "ACTION_PREVIOUS" -> playSongAt(currentIndex - 1)
            else -> {
                val song = intent?.getParcelableExtra<Song>("SONG_URL")
                val songs = intent?.getParcelableArrayListExtra<Song>("SONG_LIST")
                if (song != null && songs != null) {
                    songList = songs
                    currentIndex = songs.indexOfFirst { it.source == song.source }.takeIf { it >= 0 } ?: 0
                    playSongAt(currentIndex, startInForeground = true)
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        serviceScope.cancel()
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
        mediaSession.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun updatePlaybackState(isPlaying: Boolean) {
        val state = if (isPlaying) PlaybackStateCompat.STATE_PLAYING else PlaybackStateCompat.STATE_PAUSED
        mediaSession.setPlaybackState(
            PlaybackStateCompat.Builder()
                .setActions(
                    PlaybackStateCompat.ACTION_PLAY or
                            PlaybackStateCompat.ACTION_PAUSE or
                            PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                            PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                )
                .setState(state, exoPlayer?.currentPosition ?: 0L, 1f)
                .build()
        )
    }

    private fun playSongAt(index: Int, startInForeground: Boolean = false) {
        if (index !in songList.indices) return
        currentIndex = index
        val song = songList[index]
        currentSong = song

        exoPlayer?.apply {
            setMediaItem(MediaItem.fromUri(song.source))
            prepare()
            play()
        }

        sendBroadcast(Intent("com.example.officialmusicapp.SONG_CHANGED").apply {
            putExtra("NEW_SONG", song)
        })

        serviceScope.launch {
            val albumArt = getAlbumArtFromUrl(song.image)
            val notification = createNotification(song, albumArt)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (startInForeground) {
                startForeground(1, notification)
            } else {
                manager.notify(1, notification)
            }
        }
    }

    private fun updateNotification() {
        currentSong?.let { song ->
            serviceScope.launch {
                val albumArt = getAlbumArtFromUrl(song.image)
                val notification = createNotification(song, albumArt)
                val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(1, notification)
            }
        }
    }

    private fun createNotification(song: Song, albumArt: Bitmap?): Notification {
        val channelId = "music_channel"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Music Playback", NotificationManager.IMPORTANCE_LOW)
            manager.createNotificationChannel(channel)
        }

        val isPlaying = exoPlayer?.isPlaying == true
        val playPauseIcon = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        val playPauseAction = if (isPlaying) "ACTION_PAUSE" else "ACTION_PLAY"

        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(song.title)
            .setContentText(song.artist)
            .setSmallIcon(R.drawable.ic_zingmp3)
            .setLargeIcon(albumArt)
            .addAction(R.drawable.ic_back_song, "Previous", createPendingIntent("ACTION_PREVIOUS"))
            .addAction(playPauseIcon, "PlayPause", createPendingIntent(playPauseAction))
            .addAction(R.drawable.ic_next_song, "Next", createPendingIntent("ACTION_NEXT"))
            .setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setOnlyAlertOnce(true)
            .setOngoing(isPlaying)

        val duration = exoPlayer?.duration?.toInt()?.takeIf { it > 0 } ?: 0
        val position = exoPlayer?.currentPosition?.toInt() ?: 0

        if (duration > 0) builder.setProgress(duration, position, false)

        return builder.build()
    }

    private fun createPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, MusicPlayerService::class.java).apply { this.action = action }
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    private suspend fun getAlbumArtFromUrl(url: String?): Bitmap? {
        if (url.isNullOrEmpty()) {
            return BitmapFactory.decodeResource(resources, R.drawable.ic_default_song)
        }

        val loader = ImageLoader(this)
        val request = ImageRequest.Builder(this).data(url).build()
        val result = loader.execute(request)
        return if (result is SuccessResult) result.drawable.toBitmap()
        else BitmapFactory.decodeResource(resources, R.drawable.ic_default_song)
    }
}
