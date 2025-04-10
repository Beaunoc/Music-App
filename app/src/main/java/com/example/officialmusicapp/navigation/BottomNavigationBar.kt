package com.example.officialmusicapp.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.officialmusicapp.R
import com.example.officialmusicapp.data.model.entities.NavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        NavigationItem(
            "Library",
            "library_screen",
            R.drawable.ic_tab_library
        ),
        NavigationItem(
            "Discovery",
            "discovery_screen",
            R.drawable.ic_tab_discovery
        ),
        NavigationItem(
            "Zingchart",
            "zingchart_screen",
            R.drawable.ic_tab_zing_chart
        ),
        NavigationItem(
            "Radio",
            "radio_screen",
            R.drawable.ic_tab_radio
        ),
        NavigationItem(
            "Following",
            "following_screen",
            R.drawable.ic_tab_following
        )
    )

    BottomNavigation {
        val currentBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry.value?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                selectedContentColor = Color(0xFF4000FF),
                unselectedContentColor = Color.Black
            )

        }
    }
}