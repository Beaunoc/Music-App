@file:Suppress("DEPRECATION")

package com.example.officialmusicapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Slider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.RotatingImageCard
import com.example.officialmusicapp.utils.FormatDuration
import com.example.officialmusicapp.viewmodel.SongViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicPlayerScreen(
    navController: NavController,
    viewModel: SongViewModel,
    innerPadding: PaddingValues,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    val currentSong by viewModel.currentPlayingSong.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val songDuration = (currentSong?.duration?.toLong() ?: 1L) * 1000L

    val isShuffle by viewModel.isShuffleEnabled.collectAsState()
    var isSeeking by remember { mutableStateOf(false) }
    var seekbarValue by remember { mutableFloatStateOf(0f) }

    val progress = currentPosition.toFloat() / songDuration.toFloat()
    val duration = if (isSeeking) seekbarValue else progress

    LaunchedEffect(Unit) {
        viewModel.registerBroadcasts(context)
    }

    LaunchedEffect(currentPosition) {
        if (!isSeeking) {
            seekbarValue = progress
        }
    }

    LaunchedEffect(currentSong) {
        if (currentSong != viewModel.currentPlayingSong.value) {
            viewModel.startMusicService(context, currentSong!!)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter(currentSong?.image),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.5f),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = { /* More options */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_more),
                        contentDescription = "More",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            currentSong?.image?.let { image ->
                RotatingImageCard(imageUrl = image, isPlaying = isPlaying)
            }

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /* Share */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "Share",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = currentSong?.title ?: "",
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = currentSong?.artist ?: "", color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(onClick = { /* Like */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_music_play_screen_heart),
                        contentDescription = "Heart",
                        modifier = Modifier.size(18.dp),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier.height(50.dp)) {
                Slider(
                    value = duration,
                    onValueChange = {
                        isSeeking = true
                        seekbarValue = it
                    },
                    onValueChangeFinished = {
                        val seekTo = (seekbarValue * songDuration.toFloat()).toLong()
                        viewModel.seekTo(seekTo)
                        isSeeking = false
                    },
                    valueRange = 0f..1f,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .height(1.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.White,
                        activeTrackColor = Color.White
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val displayPos = (duration * songDuration).toLong()
                    Text(
                        text = FormatDuration.formatDuration(displayPos),
                        color = Color.White, fontSize = 12.sp
                    )
                    Text(
                        text = FormatDuration.formatDuration(songDuration),
                        color = Color.White, fontSize = 12.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    viewModel.toggleShuffle(context)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_random_play),
                        contentDescription = "Shuffle",
                        modifier = Modifier.size(20.dp),
                        tint = if (isShuffle) {
                            Color(0xFFA500D0)
                        } else {
                            Color.White
                        }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        viewModel.playPreviousSong(context)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_song),
                            contentDescription = "Previous",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = {
                            viewModel.togglePlayPause(context)
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(68.dp)
                    ) {
                        val icon = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = "PlayPause",
                            modifier = Modifier.fillMaxSize(),
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = {
                        viewModel.playNextSong(context)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_next_song),
                            contentDescription = "Next",
                            modifier = Modifier.size(18.dp),
                            tint = Color.White
                        )
                    }
                }

                IconButton(onClick = { /* Repeat */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_repeat_play),
                        contentDescription = "Repeat",
                        modifier = Modifier.size(25.dp),
                        tint = Color.White
                    )
                }
            }
        }
    }
}
