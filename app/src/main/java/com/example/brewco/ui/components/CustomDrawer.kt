package com.example.brewco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.ui.screens.login.AuthViewModel
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.Cream
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomDrawer(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    onLogoutClick: () -> Unit
) {
    val authState by authViewModel.authState.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(0.45f)
            .fillMaxHeight()
            .background(
                color = Brown,
                shape = RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Icono del usuario
            Image(
                painter = painterResource(id = R.drawable.logo_brew_co),
                contentDescription = "Perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Cream)
            )

            // Texto del usuario
            Text(
                text = "Usuario",
                color = Cream,
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            // Botón de cierre de sesión
            TextButton(
                onClick = {
                    authViewModel.logout()
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(300)
                        navHostController.navigate("loginScreen") {
                            popUpTo("homeScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                    onLogoutClick()
                }
            )
            {
                Text(
                    text = "Cerrar Sesión",
                    color = Cream,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    LaunchedEffect(authState) {
        if (authState is AuthViewModel.AuthState.Unauthenticated) {
            navHostController.navigate("loginScreen") {
                popUpTo(0)
                launchSingleTop = true
            }
        }
    }
}
