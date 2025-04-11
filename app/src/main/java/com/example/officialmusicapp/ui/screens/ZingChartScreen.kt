package com.example.officialmusicapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.ItemSong
import com.example.officialmusicapp.ui.components.SearchHeader
import com.example.officialmusicapp.viewmodel.SongViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ZingChartScreen(
    navController: NavController,
    viewModel: SongViewModel,
    innerPadding: PaddingValues
) {
    val context = LocalContext.current
    val songs = viewModel.songs.collectAsState().value

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF61D7D7), Color(0xFF192EE8))
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                ) {
                    SearchHeader(
                        navController,
                        profileImage = R.drawable.img_profile_default
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.img_zingchart_text),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 10.dp)
                        .size(height = 43.dp, width = 199.dp)

                )
            }

            items(songs.size) { index ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(40.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )

                    ItemSong(song = songs[index]) { selectedSong ->
                        viewModel.startMusicService(context, selectedSong)

                        navController.navigate("music_player_screen")

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}