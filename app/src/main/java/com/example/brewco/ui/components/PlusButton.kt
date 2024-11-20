package com.example.brewco.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.brewco.ui.theme.Brown

@Composable
fun PlusButton(navController: NavController, onClick: () -> Unit,) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Brown
    ) {
        Icon(
            Icons.Default.Add, contentDescription = "Agregar",
            modifier = Modifier.size(36.dp),
            Color.White
        )
    }
}