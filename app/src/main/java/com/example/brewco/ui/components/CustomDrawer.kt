package com.example.brewco.ui.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.Cream

@Composable
fun CustomDrawer(
    navHostController: NavHostController,
    onLogoutClick: () -> Unit
) {
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
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Icono de usuario",
                tint = Cream,
                modifier = Modifier.size(100.dp)
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
                    navHostController.navigate("startSplashScreen") {
                        popUpTo(0) // Limpia la pila de navegación
                    }
                    onLogoutClick()
                }
            ) {
                Text(
                    text = "Cerrar Sesión",
                    color = Cream,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
