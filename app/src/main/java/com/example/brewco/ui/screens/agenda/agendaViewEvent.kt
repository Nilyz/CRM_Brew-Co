package com.example.brewco.ui.screens.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.screens.agenda.AlertViewModel

@Composable
fun AgendaViewEvent(navHostController: NavHostController, alertId: String, viewModel: AlertViewModel = viewModel()) {

    // Estado mutable para los campos de texto
    var titulo by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var minutosInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var minutosFin by remember { mutableStateOf("") }
    var empleado by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    LaunchedEffect(alertId) {
        viewModel.getAlertById(alertId) { fetchedProduct ->
            fetchedProduct?.let {
                titulo = it.titulo
                fechaInicio = it.fechaInicio
                horaInicio = it.horaInicio
                minutosInicio = it.minutosInicio
                fechaFin = it.fechaFin
                horaFin = it.horaFin
                minutosFin = it.minutosFin
                empleado = it.empleado
                descripcion = it.descripcion
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Detalles",
                text1 = "Atrás",
                text2 = "Editar",
                navController = navHostController, // Aquí se pasa el navHostController
                onActionClick = {
                    navHostController.navigate("agendaEditEvent/{alertId}") // Navega hacia la pantalla principal
                },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Título: ${titulo}",
                    )
                }
                item {
                    Text(
                        text = "Fecha de inicio: ${fechaInicio}",
                    )
                }
                item {
                    Text(
                        text = "Hora de inicio: ${horaInicio}:${minutosInicio}",
                    )
                }
                item {
                    Text(
                        text = "Fecha de fin: ${fechaFin}",
                    )
                }
                item {
                    Text(
                        text = "Hora de fin: ${horaFin}:${minutosFin}",
                    )
                }
                item {
                    Text(
                        text = "Empleado: ${empleado}",
                    )
                }
                item {
                    Text(
                        text = "Descripción: ${descripcion}",
                    )
                }
            }
        }
    )
}

