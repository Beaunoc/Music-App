package com.example.officialmusicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ItemRecentlyPlayed(
    image: Int,
    name: String
) {
    Column(
        modifier = Modifier
            .padding(end = 20.dp)
            .wrapContentSize()
            .width(175.dp)
    ) {
        Card(
            modifier = Modifier
                .size(175.dp)
                .padding(bottom = 20.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(6.dp),
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Image Card",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}