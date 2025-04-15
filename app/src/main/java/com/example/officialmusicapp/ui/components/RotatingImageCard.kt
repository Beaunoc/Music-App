package com.example.officialmusicapp.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RotatingImageCard(
    imageUrl: String,
    isPlaying: Boolean,
) {
    var angle by remember { mutableFloatStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotationAngle = infiniteTransition.animateFloat(
        initialValue = angle,
        targetValue = if (isPlaying) angle + 360f else angle,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 25000
            }
        ), label = ""
    )

    LaunchedEffect(isPlaying) {
        if (!isPlaying) {
            angle = rotationAngle.value
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(300.dp)
            .background(Color.Transparent)
            .padding(25.dp)
    ) {
        Card(
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .size(300.dp)
                .graphicsLayer {
                    rotationZ = rotationAngle.value
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Image Song",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
