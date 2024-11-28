package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.brewco.data.model.Client

@Composable
fun CustomerDetailsScreen(
    navHostController: NavHostController,
    clientId: String,
    viewModel: CustomerViewModel = viewModel()
) {
    var client by remember { mutableStateOf<Client?>(null) }

    // Cargar el cliente desde firebase según la ID del que has clickeado
    LaunchedEffect(clientId) {
        viewModel.getClienteById(clientId) { fetchedClient ->
            client = fetchedClient // Asigna el cliente recuperado
        }
    }

    client?.let { cliente ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Detalles del Cliente",
            )
            Text(text = "Nombre: ${cliente.nombre} ${cliente.apellido}")
            Text(text = "Teléfono: ${cliente.telefono}")
            Text(text = "Puntos: ${cliente.puntos}")
            Text(text = "Última Compra: ${cliente.ultCompra}")
            Text(text = "Notas: ${cliente.notas}")
        }
    }

}
