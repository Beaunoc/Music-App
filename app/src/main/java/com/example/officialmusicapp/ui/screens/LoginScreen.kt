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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.officialmusicapp.R
import com.example.officialmusicapp.components.GradientButton

@Composable
fun LoginScreen() {
    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
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
                    modifier = Modifier.fillMaxWidth(),
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

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color.White,
                    text = "Forgot password?"
                )

                Spacer(modifier = Modifier.height(30.dp))

                GradientButton(
                    text = "Login",
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                color = Color.White,
                text = "Continue with"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.wrapContentSize()
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_login_google),
                        contentDescription = "Icon Login Google",
                        modifier = Modifier.size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.width(60.dp))

                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_login_apple),
                        contentDescription = "Icon Login Apple",
                        modifier = Modifier.size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.width(60.dp))

                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_login_facebook),
                        contentDescription = "Icon Login Facebook",
                        modifier = Modifier.size(50.dp)
                    )
                }
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
                text = "Don't have an account? "
            )
            Text(
               color = Color(0xFF4790FD) ,
                text = "Register now")
        }
    }
}