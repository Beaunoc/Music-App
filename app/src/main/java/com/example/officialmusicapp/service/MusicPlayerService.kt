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
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.officialmusicapp.R
import com.example.officialmusicapp.data.model.entities.Song
import com.example.officialmusicapp.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("DEPRECATION")
class MusicPlayerService : Service() {
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var playbackStateBuilder: PlaybackStateCompat.Builder

    companion object {
        var exoPlayer: ExoPlayer? = null
    }

    override fun onCreate() {
        super.onCreate()
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(this).build()
        }

        mediaSession = MediaSessionCompat(this, "MusicPlayerService")
        mediaSession.setCallback(object : MediaSessionCompat.Callback() {
            override fun onPlay() {
                super.onPlay()
                exoPlayer?.play()
            }

            override fun onPause() {
                super.onPause()
                exoPlayer?.pause()
            }

            override fun onSkipToNext() {
                super.onSkipToNext()
            }

            override fun onPrepare() {
                super.onPrepare()
            }

            override fun onSeekTo(pos: Long) {
                super.onSeekTo(pos)
                exoPlayer?.seekTo(pos)
            }
        })

        playbackStateBuilder = PlaybackStateCompat.Builder()
            .setActions(
                PlaybackStateCompat.ACTION_PLAY or
                PlaybackStateCompat.ACTION_PAUSE or
                PlaybackStateCompat.ACTION_SKIP_TO_NEXT or
                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
            )

        mediaSession.setPlaybackState(playbackStateBuilder.build())
        mediaSession.isActive = true

        exoPlayer?.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                super.onPlaybackStateChanged(state)
                if (state == Player.STATE_ENDED) {
                }
            }
        })
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val song = intent?.getParcelableExtra<Song>("SONG_URL") ?: return START_NOT_STICKY

        val mediaItem = MediaItem.fromUri(song.source)
        exoPlayer?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }

        GlobalScope.launch(Dispatchers.Main) {
            val albumArt = getAlbumArtFromUrl(song.image)
            startForeground(1, createNotification(albumArt))
        }

        return START_STICKY
    }

    override fun onDestroy() {
        exoPlayer?.stop()
        exoPlayer?.release()
        exoPlayer = null
        super.onDestroy()
        mediaSession.release()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotification(albumArt: Bitmap?): Notification {
        val channelId = "music_channel"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Music Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(channel)
        }

        val playPendingIntent = createPendingIntent("ACTION_PLAY")
        val pausePendingIntent = createPendingIntent("ACTION_PAUSE")
        val nextPendingIntent = createPendingIntent("ACTION_NEXT")
        val previousPendingIntent = createPendingIntent("ACTION_PREVIOUS")

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle(songViewModel.currentPlayingSong.value?.title)
            .setContentText(songViewModel.currentPlayingSong.value?.artist)
            .setSmallIcon(R.drawable.ic_zingmp3)
            .setLargeIcon(albumArt)
            .addAction(R.drawable.ic_back_song, "Previous", previousPendingIntent)
            .addAction(R.drawable.ic_play, "Play", playPendingIntent)
            .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)
            .addAction(R.drawable.ic_next_song, "Next", nextPendingIntent)
            .setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
                    .setShowActionsInCompactView(1,2,3,4)
            )
            .setProgress(100, 0, false)
            .build()
    }

    private fun createPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, MusicPlayerService::class.java)
        intent.action = action
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private suspend fun getAlbumArtFromUrl(url: String?): Bitmap?{
        if (url.isNullOrEmpty()) {
            return BitmapFactory.decodeResource(resources, R.drawable.ic_default_song)
        }

        val imageLoader = ImageLoader(this)

        val request = ImageRequest.Builder(this)
            .data(url)
            .build()

        return when (val response = imageLoader.execute(request)) {
            is SuccessResult -> {
                response.drawable.toBitmap()
            }
            is ErrorResult -> {
                BitmapFactory.decodeResource(resources, R.drawable.ic_default_song)
            }
            else -> {
                BitmapFactory.decodeResource(resources, R.drawable.ic_default_song)
            }
        }
    }

}
