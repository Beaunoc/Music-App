package com.example.officialmusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.officialmusicapp.ui.screens.DownloadedSongScreen
import com.example.officialmusicapp.ui.screens.MusicPlayerScreen
import com.example.officialmusicapp.viewmodel.SongViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val songViewModel: SongViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "downloaded_screen"
    ) {
        composable("downloaded_screen") {
            DownloadedSongScreen(
                navController,
                songViewModel
            )
        }

        composable("music_player_screen") {
            MusicPlayerScreen(
                navController,
                songViewModel
            )
        }
    }
}