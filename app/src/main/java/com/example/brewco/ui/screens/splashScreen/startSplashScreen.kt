package com.example.brewco.ui.screens.splashScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewco.R
import com.example.brewco.ui.components.CustomButton
import com.example.brewco.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewco.ui.screens.login.AuthViewModel


@Composable
fun StartSplashScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val authViewModel: AuthViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Beige),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logobrew_corecortado_transformed),
            contentDescription = "Logo",
            modifier = Modifier
                .size(250.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        Box(
            modifier = modifier.fillMaxWidth(0.6f)
        ) {
            CustomButton(
                text = "Ir a inicio de sesión",
                textColor = Cream,
                contColor = Brown,
                fontSize = 18.sp,
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    navHostController.navigate("loginScreen") {
                        popUpTo("startSplashScreen") { inclusive = true }
                    }
                }
            )
        }
    }
}

