package com.example.officialmusicapp.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.ItemLibrary
import com.example.officialmusicapp.ui.components.ItemPlaylist
import com.example.officialmusicapp.ui.components.ItemRecentlyPlayed
import com.example.officialmusicapp.ui.components.SearchHeader
import com.example.officialmusicapp.viewmodel.SongViewModel

@Composable
fun SongLibraryScreen(
    navController: NavController,
    viewModel: SongViewModel,
    paddingValues: PaddingValues
) {
    val columnScrollState = rememberScrollState()
    val rowLibraryScrollState = rememberScrollState()
    val rowRecentlyPlayedScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .verticalScroll(columnScrollState)
    ) {
        SearchHeader(
            navController,
            profileImage = R.drawable.img_profile_default
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Library",
            style = TextStyle(fontSize = 36.sp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rowLibraryScrollState)
        ) {
            ItemLibrary(
                imageIcon = R.drawable.ic_card_library_heart,
                text = "Favorite Songs",
                numberOfItems = 0,
                onItemClick = {
                    navController.navigate("favorite_screen")
                }
            )

            ItemLibrary(
                imageIcon = R.drawable.ic_card_library_download,
                text = "Downloaded",
                numberOfItems = 0,
                onItemClick = {
                    navController.navigate("download_screen")
                }
            )

            ItemLibrary(
                imageIcon = R.drawable.ic_card_library_artist,
                text = "Artists",
                numberOfItems = 0,
                onItemClick = {
                    navController.navigate("artist_screen")
                }
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Recently listened >",
            style = TextStyle(fontSize = 24.sp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rowRecentlyPlayedScrollState)
        ) {
            ItemRecentlyPlayed(
                image = R.drawable.img_recently_played_song,
                name = "Recently Played Songs"
            )
            ItemRecentlyPlayed(
                image = R.drawable.img_recently_played_song,
                name = "Recently Played Songs"
            )
            ItemRecentlyPlayed(
                image = R.drawable.img_recently_played_song,
                name = "Recently Played Songs"
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Playlist",
            style = TextStyle(fontSize = 24.sp),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")
        Spacer(modifier = Modifier.height(20.dp))

        ItemPlaylist(imageId = R.drawable.ic_create_playlist, namePlaylist = "Create playlist")

    }
}