package com.example.brewco.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.brewco.R
import com.example.brewco.ui.theme.*




@Composable
fun StartSplashScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brown),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logobrewco),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 8.dp)
        )
        Text("Brew & Co")
        Box(
            modifier = modifier
                .size(150.dp)
        ) {
            Button(
                onClick = {
                    navHostController.navigate("loginScreen")
                },
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)
            ) {
                Text("Ir a Login")
            }
        }

    }

}
