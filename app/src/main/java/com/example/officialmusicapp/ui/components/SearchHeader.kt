package com.example.officialmusicapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.officialmusicapp.R

@Composable
fun SearchHeader(
    navController: NavController,
    profileImage: Int,
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Profile icon
        IconButton(
            onClick = { /*TODO*/ },
            Modifier.size(60.dp)
        ) {
            Image(
                painter = painterResource(id = profileImage),
                contentDescription = "Profile Icon",
            )
        }

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text(text = "Search for songs, artists...")
            },
            shape = RoundedCornerShape(80.dp),
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
                .padding(horizontal = 10.dp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF9D0DA),
                unfocusedContainerColor = Color(0xFFF9D0DA),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 10.dp)
                .size(24.dp)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notification",
                modifier = Modifier.fillMaxSize()
            )
        }

    }

}