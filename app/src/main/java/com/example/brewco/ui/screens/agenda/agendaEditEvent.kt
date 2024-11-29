package com.example.brewco.ui.screens.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.brewco.data.model.Alert
import androidx.compose.foundation.lazy.LazyColumn
import com.example.brewco.ui.components.TopBarWithText
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AgendaEditEvent(navHostController: NavHostController, alertId: String, viewModel: AlertViewModel = viewModel()) {

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
                title = "Editar Evento",
                text1 = "Atrás",
                text2 = "Guardar",
                navController = navHostController,
                onActionClick = {
                    // Aquí puedes agregar lógica para guardar los cambios
                    // Luego, navegas hacia la pantalla de agenda o donde quieras
                    navHostController.navigate("agendaScreen")
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
                    TextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Título") }
                    )
                }
                item {
                    TextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") }
                    )
                }
                item {
                    TextField(
                        value = fechaInicio,
                        onValueChange = { fechaInicio = it },
                        label = { Text("Fecha de Inicio") }
                    )
                }
                item {
                    TextField(
                        value = horaInicio,
                        onValueChange = { horaInicio = it },
                        label = { Text("Hora de Inicio") }
                    )
                }
                item {
                    TextField(
                        value = minutosInicio,
                        onValueChange = { minutosInicio = it },
                        label = { Text("Minutos de Inicio") }
                    )
                }
            }
        }
    )
}
