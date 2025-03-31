package com.example.officialmusicapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.officialmusicapp.ui.screens.LoginScreen
import com.example.officialmusicapp.ui.screens.RegisterScreen
import com.example.officialmusicapp.ui.screens.SplashScreen
import com.example.officialmusicapp.ui.theme.OfficialMusicAppTheme
import com.example.officialmusicapp.viewmodel.SongViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SongViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OfficialMusicAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    var isSplashScreenFinished by remember {
//                        mutableStateOf(false)
//                    }
//
//                    if (!isSplashScreenFinished) {
//                        SplashScreen(
//                            onTimeOut = { isSplashScreenFinished = true }
//                        )
//                    } else {
//                        HomeScreen()
//                    }

//                    LoginScreen()

                    RegisterScreen()
                }
            }
        }
    }

    @Composable
    fun HomeScreen() {
        // Màn hình chính sau khi Splash hoàn tất
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Welcome to Music App!",
            )
        }
    }
}
