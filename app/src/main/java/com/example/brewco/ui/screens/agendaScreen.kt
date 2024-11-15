package com.example.brewco.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.brewco.ui.components.CustomBottomBar
import com.example.brewco.ui.components.CustomTopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.brewco.ui.components.CustomBottomBar
import com.example.brewco.ui.components.CustomTopBar

@Composable
fun AgendaScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopBar(title = "Agenda")
        },
        bottomBar = {
            CustomBottomBar(navController = navHostController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Espacio para evitar solapamiento con las barras
        ) {
            Text(
                text = "Bienvenido a la Agenda",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Aquí iría tu contenido principal.",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AgendaScreenPreview() {
    AgendaScreen(navHostController = rememberNavController())
}