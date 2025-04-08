package com.example.officialmusicapp.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.officialmusicapp.R

class MusicPlayerService : Service() {
    private lateinit var handler: Handler
    private lateinit var updateRunnable: Runnable

    companion object {
        var exoPlayer: ExoPlayer? = null
    }

    override fun onCreate() {
        super.onCreate()
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(this).build()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val songUrl = intent?.getStringExtra("SONG_URL") ?: return START_NOT_STICKY

        val mediaItem = MediaItem.fromUri(songUrl)
        exoPlayer?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }

        startForeground(1, createNotification())
        return START_STICKY
    }

    override fun onDestroy() {
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(): Notification {
        val channelId = "music_channel"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Music Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Now Playing")
            .setContentText("Enjoy your song!")
            .setSmallIcon(R.drawable.ic_zingmp3)
            .build()
    }

}
