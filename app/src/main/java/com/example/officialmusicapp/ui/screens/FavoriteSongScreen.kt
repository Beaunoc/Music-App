package com.example.officialmusicapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.ItemNoSong
import com.example.officialmusicapp.ui.components.ItemSong
import com.example.officialmusicapp.viewmodel.SongViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteSongScreen(
    navController: NavController,
    viewModel: SongViewModel
) {

    val songList = listOf<String>(
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về",
        "Gương Mặt Lạ Lẫm",
        "Lặng Thầm Một Tình Yêu",
        "Chắc Ai Đó Sẽ Về",
        "Em Gái Mưa",
        "Về Đây Em Lo",
        "Đi Để Trở Về"
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.padding(end = 8.dp)

                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp)
                        )

                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp)
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Favorite Songs",
                        style = TextStyle(fontSize = 25.sp),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "${songList.size} songs saved to Library")

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            Color(0xFFAB43AD)
                        )
                    ) {
                        Text(text = "Random Play")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

//                    ItemSong(
//                        imageId = R.drawable.ic_login_facebook,
//                        nameOfSong = "Ok baby",
//                        nameOfArtist = "Unknown",
//                        isFavorite = true
//                    )
//
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    ItemSong(
//                        imageId = R.drawable.ic_login_facebook,
//                        nameOfSong = "Ok baby",
//                        nameOfArtist = "Unknown",
//                        isFavorite = true
//                    )
//
//                    Spacer(modifier = Modifier.height(10.dp))
//
//                    ItemSong(
//                        imageId = R.drawable.ic_login_facebook,
//                        nameOfSong = "Ok baby",
//                        nameOfArtist = "Unknown",
//                        isFavorite = true
//                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }

                if (songList.isEmpty()) {
                    item {
                        ItemNoSong(text = "There are no favorite songs")
                    }
                } else {
                    items(songList.size) { index ->
                        Text(text = songList[index])
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }


            }
        }
    )
}