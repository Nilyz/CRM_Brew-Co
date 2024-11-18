package com.example.brewco.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.brewco.ui.components.CustomerCard
import com.example.brewco.ui.components.CustomFloatingActionButton
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.components.CustomerCard as CustomerCard
data class Customer(
    val name: String,
    val lastName: String,
    val points: Int,
    val phone: String, // Añadimos este campo
    val lastPurchase: String
)

@Composable
fun CustomerScreen(navHostController: NavHostController) {
    // Lista de clientes con teléfonos
    val customers = listOf(
        Customer("Carlos García Lorca", "García Lorca", 50, "123 456 789", "20/07/2024"),
        Customer("María", "López", 75, "987 654 321", "15/08/2024"),
        Customer("Juan", "Pérez", 100, "111 222 333", "10/09/2024"),
        Customer("Ana", "Martínez", 45, "444 555 666", "05/06/2024")
    )

    Scaffold(
        topBar = { TopBar(title = "Clientes") },
        bottomBar = { CustomBottomNavBar(navHostController) },
        floatingActionButton = {
            CustomFloatingActionButton(navHostController)
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Dos columnas
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(customers) { customer ->
                    CustomerCard(
                        name = customer.name,
                        lastName = customer.lastName,
                        points = customer.points,
                        phone = customer.phone, // Pasamos el teléfono
                        lastPurchase = customer.lastPurchase
                    )
                }
            }
        }
    )
}



