package com.example.brewco.ui.components

import android.provider.SyncStateContract.Columns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.ui.theme.Brown

@Composable
fun CustomDrawer(
    navHostController: NavHostController,
    onLogoutClik: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxSize()
            .background(color = Brown)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Aquí se implementa el contenido del Drawer
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Aquí se implementa el contenido del Drawer
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Icono de usuario",
                tint = Black,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Usuario",
                color = Black,
            )
            TextButton(
                onClick = {
                    navHostController.navigate("loginScreen")
                    onLogoutClik()
                }
            ) {
                Text(
                    text = "Inicio",
                    color = Black,
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }
}