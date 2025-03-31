package com.example.officialmusicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.officialmusicapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFAB43AD)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_zingmp3),
            contentDescription = "Logo App Music",
            modifier = Modifier
                .padding(50.dp)
                .size(200.dp)

        )

        Image(
            painter = painterResource(id = R.drawable.ic_txt_zingmp3),
            contentDescription = "Logo Text Music App",
            modifier = Modifier.size(width = 277.dp, height = 67.dp)
        )

    }

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeOut()
    }
}