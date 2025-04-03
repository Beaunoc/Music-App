package com.example.officialmusicapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.officialmusicapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DownloadedSongScreen() {
    val tabTitles = listOf("Songs", "Playlist", "Album")

    val pagerState = rememberPagerState(
        pageCount = { tabTitles.size }
    )

    val coroutineScope = rememberCoroutineScope()


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

                    Text(text = " songs saved to Library")

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

                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                    ) {
                        tabTitles.forEachIndexed { index, title ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(index)
                                    }
                                },

                                ) {
                                Text(
                                    text = title,
                                    fontWeight = FontWeight.Bold,
                                    color = if (pagerState.currentPage == index) Color.Black else Color.Gray
                                )
                            }
                        }
                    }

                    HorizontalPager(
                        state = pagerState
                    ) { page ->
                        when (page) {
                            0 -> TabSong()
                            1 -> TabPlaylist()
                            2 -> TabAlbum()
                        }
                    }

                }
            }
        }
    )
}


@Composable
fun TabSong() {
    LazyColumn {

    }
}

@Composable
fun TabPlaylist() {
    Text(text = "abc")
}

@Composable
fun TabAlbum() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "abcdef")
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "abcdef")
        Text(text = "abcdef")

        Text(text = "abcdef")
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "abcdef")
        Text(text = "abcdef")


        Text(text = "abcdef")
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "abcdef")
        Text(text = "abcdef")


        Text(text = "abcdef")
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "abcdef")
        Text(text = "abcdef")


        Text(text = "abcdef")
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "abcdef")
        Text(text = "abcdef")


    }

}