package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Client
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.*

@Composable
fun CustomerDetailsScreen(
    navHostController: NavHostController,
    clientId: String,
    viewModel: CustomerViewModel = viewModel()
) {
    var client by remember { mutableStateOf<Client?>(null) }

    // Cargar el cliente desde Firebase
    LaunchedEffect(clientId) {
        viewModel.getClienteById(clientId) { fetchedClient ->
            client = fetchedClient
        }
    }

    client?.let { cliente ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Padding general para toda la pantalla
            verticalArrangement = Arrangement.spacedBy(24.dp) // Espaciado entre la información principal y las notas
        ) {
            // Contenedor principal de la información del cliente
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding interno para centrar visualmente el contenido
                    .align(Alignment.CenterHorizontally), // Centrar horizontalmente
                verticalArrangement = Arrangement.spacedBy(12.dp), // Espaciado entre filas
                horizontalAlignment = Alignment.CenterHorizontally // Asegura que todo el contenido quede centrado horizontalmente
            ) {
                // Nombre del cliente
                Text(
                    text = "${cliente.nombre} ${cliente.apellido}",
                    fontWeight = FontWeight.Bold,
                    color = Brown
                )

                // Teléfono
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Phone, contentDescription = "Teléfono", tint = Brown)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = cliente.telefono,
                        fontWeight = FontWeight.Normal,
                        color = Brown
                    )
                }

                // Última compra
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.DateRange, contentDescription = "Última compra", tint = Brown)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Última compra: ${cliente.ultCompra}",
                        fontWeight = FontWeight.Normal,
                        color = Brown
                    )
                }

                // Correo electrónico
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Email, contentDescription = "Correo", tint = Brown)
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Correo: ${cliente.correo}",
                        fontWeight = FontWeight.Normal,
                        color = Brown
                    )
                }

                // Puntos
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Default.CheckCircle, contentDescription = "Agregar",
                        modifier = Modifier.size(20.dp),
                        tint = Brown
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Puntos: ${cliente.puntos}",
                        fontWeight = FontWeight.Bold,
                        color = Brown
                    )
                    Icon(
                        Icons.Default.Star, contentDescription = "Agregar",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Yellow
                    )
                }
            }

            // Sección de notas
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Notas:",
                    fontWeight = FontWeight.Bold,
                    color = Brown
                )
                Text(
                    text = "${cliente.notas}",
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
            }
        }
    }
}
