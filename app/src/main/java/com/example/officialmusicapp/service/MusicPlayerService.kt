package com.example.officialmusicapp.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.officialmusicapp.R
import com.example.officialmusicapp.data.model.entities.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


@Suppress("DEPRECATION")
class MusicPlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private val serviceScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Log.d("MusicPlayerService", "Service created")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song = intent?.getParcelableExtra<Song>("song")



        if (song != null) {
            startPlaying(song)

            serviceScope.launch {
                val albumArt = getAlbumArt(song.image)
                if (albumArt != null) {
                    createNotification(song, albumArt)
                }
            }
        }

        return START_STICKY
    }

    private fun startPlaying(song: Song) {
        mediaPlayer = MediaPlayer().apply {
            setDataSource(song.source)
            prepareAsync()
            setOnPreparedListener {
                start()
            }

            setOnCompletionListener {
                stopSelf()
            }

            setOnErrorListener { mp, what, extra ->
                Log.e("MusicPlayerService", "Error during playback: $what")
                stopSelf()
                true
            }
        }
    }

    @SuppressLint("ForegroundServiceType")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(song: Song, albumArt: Bitmap) {
        val channelId = "music_channel"

        val notificationManager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Music Playback", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = Notification.Builder(this, channelId)
            .setContentTitle(song.title)
            .setContentText(song.artist)
            .setSmallIcon(R.drawable.ic_zingmp3)
            .setOngoing(true)
            .setLargeIcon(albumArt)
            .addAction(
                if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play,
                if (isPlaying) "Pause" else "Play",
                getPlayPauseIntent()
            )
            .addAction(R.drawable.ic_next_song, "Next", getNextIntent())
            .addAction(R.drawable.ic_back_song, "Back", getPreviousIntent())
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(1, notification, FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK)
        } else {
            startForeground(1, notification)
        }
    }

    private suspend fun getAlbumArt(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                // Tải ảnh album từ URL
                val inputStream = URL(imageUrl).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun getPlayPauseIntent(): PendingIntent {
        val playPauseIntent = Intent(this, MusicPlayerService::class.java).apply {
            action = if (isPlaying) "Pause" else "Play"
        }

        return PendingIntent.getService(this, 0, playPauseIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getNextIntent(): PendingIntent {
        val nextIntent = Intent(this, MusicPlayerService::class.java).apply {
            action = "Next"
        }

        return PendingIntent.getService(this, 0, nextIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getPreviousIntent(): PendingIntent {
        val previousIntent = Intent(this, MusicPlayerService::class.java).apply {
            action = "PREVIOUS"
        }
        return PendingIntent.getService(this, 0, previousIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        Log.d("MusicPlayerService", "Service destroyed")
    }


}