package com.example.officialmusicapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.officialmusicapp.R
import com.example.officialmusicapp.data.model.entities.Song

@Composable
fun ItemSong(
    song: Song,
    onclick: (Song) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onclick(song) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = song.image,
                placeholder = painterResource(id = R.drawable.img_recently_played_song)
            ),
            contentDescription = "Image Song",
            Modifier.size(height = 46.dp, width = 65.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = song.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = song.artist,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        if (song.favorite.toBoolean()) {
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