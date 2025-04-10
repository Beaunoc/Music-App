package com.example.officialmusicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.officialmusicapp.R
import com.example.officialmusicapp.ui.components.GradientButton
import com.example.officialmusicapp.viewmodel.SongViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: SongViewModel
) {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFAB43AD)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_txt_zingmp3),
                contentDescription = "Logo Text Music App",
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .size(width = 364.dp, height = 87.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {

                //username input field
                TextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                    label = { Text(text = "Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color(0xFF978B8B),
                        unfocusedLabelColor = Color(0xFF978B8B),
                        focusedContainerColor = Color(0xFFFFFFFF),
                        unfocusedContainerColor = Color(0xFFDBC3DB),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true
                )

                //password input field
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color(0xFF978B8B),
                        unfocusedLabelColor = Color(0xFF978B8B),
                        focusedContainerColor = Color(0xFFFFFFFF),
                        unfocusedContainerColor = Color(0xFFDBC3DB),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                //show error if confirm password is not same as password
                if (password != confirmPassword && confirmPassword.isNotEmpty()) {
                    Text(
                        color = Color(0xFFFFFFFF),
                        text = "Password does not match",
                        style = TextStyle(fontSize = 15.sp)
                    )
                }

                //Confirm password input field
                TextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                    },
                    label = { Text(text = "Confirm Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color(0xFF978B8B),
                        unfocusedLabelColor = Color(0xFF978B8B),
                        focusedContainerColor = Color(0xFFFFFFFF),
                        unfocusedContainerColor = Color(0xFFDBC3DB),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                //Email input field
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = Color(0xFF978B8B),
                        unfocusedLabelColor = Color(0xFF978B8B),
                        focusedContainerColor = Color(0xFFFFFFFF),
                        unfocusedContainerColor = Color(0xFFDBC3DB),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true
                )

                GradientButton(
                    text = "Register",
                    onClick = {}
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                color = Color.White,
                text = "You have an account? "
            )
            Text(
                color = Color(0xFF4790FD),
                text = "Login now!"
            )
        }
    }
}