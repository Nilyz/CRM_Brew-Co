package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomFloatingActionButton
import com.example.brewco.ui.components.TopBar

@Composable
fun CustomerScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = { TopBar(title = "Clientes") },
        bottomBar = { CustomBottomNavBar(navHostController) },
        floatingActionButton = {
            CustomFloatingActionButton(navHostController)
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Simulamos contenido
                    items(50) { index ->
                        CustomerItem(index)
                    }
                }
            }
        }
    )
}


@Composable
fun CustomerItem(index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
    ) {
        Text(
            text = "Evento $index",
            modifier = Modifier
                .padding(16.dp),


            )
    }
}
