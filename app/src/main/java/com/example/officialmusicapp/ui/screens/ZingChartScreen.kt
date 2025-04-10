package com.example.officialmusicapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.ItemSong
import com.example.officialmusicapp.ui.components.SearchHeader
import com.example.officialmusicapp.viewmodel.SongViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ZingChartScreen(
    navController: NavController,
    viewModel: SongViewModel
) {
    val context = LocalContext.current
    val songs = viewModel.songs.collectAsState().value

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF61D7D7), Color(0xFF192EE8))
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        item {
            SearchHeader(
                navController,
                profileImage = R.drawable.img_profile_default
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Zingchart",
                style = TextStyle(fontSize = 36.sp),
                fontWeight = FontWeight.Bold
            )
        }

        items(songs.size){index ->
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
                Spacer(modifier = Modifier.width(10.dp))

                ItemSong(song = songs[index]) { selectedSong ->
                    viewModel.startMusicService(context, selectedSong)

                    navController.navigate("music_player_screen")

                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}