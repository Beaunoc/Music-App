package com.example.officialmusicapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.RotatingImageCard
import com.example.officialmusicapp.viewmodel.SongViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerScreen(
    navController: NavController,
    viewModel: SongViewModel
) {
    val song by viewModel.currentPlayingSong.collectAsState(initial = null)

    Log.d("MusicPlayerABC", "Current song: $song")

    var isPlaying by remember {
        mutableStateOf(false)
    }

    var rotationAngle by remember {
        mutableFloatStateOf(0f)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(viewModel.currentPlayingSong.value?.image),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.5f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(end = 8.dp)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_more),
                        contentDescription = "Icon More",
                        modifier = Modifier.size(30.dp)
                    )

                }


            }

            Spacer(modifier = Modifier.height(150.dp))

            if (song != null) {
                RotatingImageCard(
                    imageUrl = song!!.image,
                    isPlaying = isPlaying
                )
            }

            Spacer(modifier = Modifier.height(100.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "Icon Share",
                        modifier = Modifier.size(30.dp),
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    if (song!=null){
                        Text(text = song!!.title, color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = song!!.artist, color = Color.White)
                    }
                    
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_music_play_screen_heart),
                        contentDescription = "Icon Heart",
                        modifier = Modifier.size(30.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {

                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_random_play),
                        contentDescription = "Icon Shuffle",
                        modifier = Modifier.size(36.dp),
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_song),
                            contentDescription = "Icon Previous",
                            modifier = Modifier.size(30.dp),
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_pause),
                            contentDescription = "Icon Share",
                            modifier = Modifier.size(60.dp),
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_next_song),
                            contentDescription = "Icon Share",
                            modifier = Modifier.size(30.dp),
                        )
                    }
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_repeat_play),
                        contentDescription = "Icon Repeat",
                        modifier = Modifier.size(30.dp),
                    )
                }


            }

        }
    }

}