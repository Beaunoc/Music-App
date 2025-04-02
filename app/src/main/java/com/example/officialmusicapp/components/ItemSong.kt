package com.example.officialmusicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.officialmusicapp.R

@Composable
fun ItemSong(
    imageId: Int,
    nameOfSong: String,
    nameOfArtist: String,
    isFavorite: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Image Song",
            Modifier.size(height = 46.dp, width = 65.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = nameOfSong)
            Text(text = nameOfArtist)
        }
        Spacer(modifier = Modifier.weight(1f))
        if (isFavorite) {
            Image(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = "Icon Favorite",
                modifier = Modifier.size(24.dp)
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_more),
                contentDescription = "Icon More"
            )
        }

    }
}