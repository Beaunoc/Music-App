package com.example.officialmusicapp.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.officialmusicapp.ui.screens.DiscoveryScreen
import com.example.officialmusicapp.ui.screens.DownloadedSongScreen
import com.example.officialmusicapp.ui.screens.FavoriteSongScreen
import com.example.officialmusicapp.ui.screens.FollowingScreen
import com.example.officialmusicapp.ui.screens.LoginScreen
import com.example.officialmusicapp.ui.screens.MusicPlayerScreen
import com.example.officialmusicapp.ui.screens.NotificationScreen
import com.example.officialmusicapp.ui.screens.PersonalProfileScreen
import com.example.officialmusicapp.ui.screens.ArtistScreen
import com.example.officialmusicapp.ui.screens.RadioScreen
import com.example.officialmusicapp.ui.screens.RegisterScreen
import com.example.officialmusicapp.ui.screens.SearchScreen
import com.example.officialmusicapp.ui.screens.SettingScreen
import com.example.officialmusicapp.ui.screens.SongLibraryScreen
import com.example.officialmusicapp.ui.screens.ZingChartScreen
import com.example.officialmusicapp.viewmodel.SongViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val songViewModel: SongViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "zingchart_screen",
        ) {
            composable("downloaded_screen") {
                DownloadedSongScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("music_player_screen") {
                MusicPlayerScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("library_screen") {
                SongLibraryScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("discovery_screen") {
                DiscoveryScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("zingchart_screen") {
                ZingChartScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("radio_screen") {
                RadioScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("following_screen") {
                FollowingScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("download_screen") {
                DownloadedSongScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("favorite_screen") {
                FavoriteSongScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("login_screen") {
                LoginScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("register_screen") {
                RegisterScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("notification_screen") {
                NotificationScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("personal_profile_screen") {
                PersonalProfileScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("artist_screen") {
                ArtistScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("search_screen") {
                SearchScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
            composable("setting_screen") {
                SettingScreen(
                    navController,
                    songViewModel,
                    innerPadding
                )
            }
        }
    }
}