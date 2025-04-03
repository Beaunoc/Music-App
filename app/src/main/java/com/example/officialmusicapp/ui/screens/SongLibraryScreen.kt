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
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.ItemLibrary
import com.example.officialmusicapp.ui.components.ItemPlaylist
import com.example.officialmusicapp.ui.components.ItemRecentlyPlayed
import com.example.officialmusicapp.ui.components.SearchHeader

@Composable
fun SongLibraryScreen(
    paddingValues: PaddingValues
) {
    val columnScrollState = rememberScrollState()
    val rowLibraryScrollState = rememberScrollState()
    val rowRecentlyPlayedScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .verticalScroll(columnScrollState)
    ) {
        SearchHeader(profileImage = R.drawable.img_profile_default)
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
            ItemLibrary(imageIcon = R.drawable.ic_card_library_heart, text = "Favorite Songs", 0)

            ItemLibrary(imageIcon = R.drawable.ic_card_library_download, text = "Downloaded", 0)

            ItemLibrary(imageIcon = R.drawable.ic_card_library_artist, text = "Artists", 0)
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