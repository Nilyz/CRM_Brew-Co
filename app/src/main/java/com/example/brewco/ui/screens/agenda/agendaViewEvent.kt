package com.example.brewco.ui.screens.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.screens.agenda.AlertViewModel
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.DarkBrown

@Composable
fun AgendaViewEvent(navHostController: NavHostController, alertId: String, viewModel: AlertViewModel = viewModel()) {
    var titulo by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var minutosInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var minutosFin by remember { mutableStateOf("") }
    var empleado by remember { mutableStateOf("") }
    var aviso by remember { mutableStateOf(false) } // Default como falso
    var descripcion by remember { mutableStateOf("") }

    // Cargar datos
    LaunchedEffect(alertId) {
        viewModel.getAlertById(alertId) { fetchedAlert ->
            fetchedAlert?.let {
                titulo = it.titulo
                fechaInicio = it.fechaInicio
                horaInicio = it.horaInicio
                minutosInicio = it.minutosInicio
                fechaFin = it.fechaFin
                horaFin = it.horaFin
                minutosFin = it.minutosFin
                empleado = it.empleado
                aviso = it.aviso
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
                navController = navHostController,
                onActionClick = {
                    navHostController.navigate("agendaEditEvent/$alertId")
                },
            )
        },
        containerColor = Color.White, // Fondo blanco para toda la pantalla
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp) // Espacio para el botón
                        .background(Color.White),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp) // Más espacio entre elementos
                ) {
                    // Título
                    item {
                        Text(
                            text = titulo,
                            fontSize = 26.sp,        // Tamaño de fuente
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown
                        )
                    }
                    // Fechas y horarios
                    item {
                        Column {
                            Text(
                                text = "$fechaInicio - $fechaFin",
                                color = DarkBrown,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "De $horaInicio:$minutosInicio a $horaFin:$minutosFin",
                                color = DarkBrown,
                                fontSize = 18.sp
                            )
                        }
                    }
                    // Empleado
                    item {
                        Text(
                            text = "Empleado: $empleado",
                            color = DarkBrown,
                            fontSize = 18.sp
                        )
                    }
                    // Aviso
                    item {
                        Text(
                            text = "Aviso: ${if (aviso) "Sí" else "No"}",
                            color = DarkBrown,
                            fontSize = 18.sp
                        )
                    }
                    // Notas / Descripción
                    item {
                        Column {
                            Text(
                                text = "Notas",
                                color = DarkBrown,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp)) // Esquinas redondeadas
                                    .background(Beige) // Fondo beige claro
                                    .padding(16.dp) // Espaciado interno
                            ) {
                                Text(
                                    text = descripcion,
                                    color = DarkBrown,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
                // Botón de eliminar en la parte inferior
                Button(
                    onClick = {
                        viewModel.deleteAlert(
                            alertId = alertId,
                            onSuccess = {
                                navHostController.navigate("agendaScreen?delete=true") {
                                    popUpTo("agendaScreen") { inclusive = true }
                                }
                            },
                            onError = { errorMessage ->
                            }
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Botón en la parte inferior
                        .padding(16.dp)
                        .wrapContentWidth() // Ajustar ancho al contenido del texto
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp), // Esquinas redondeadas
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White // Fondo blanco del botón
                    )
                ) {
                    Text(
                        text = "Eliminar evento",
                        color = Color.Red, // Texto en rojo
                        fontSize = 16.sp
                    )
                }

            }
        }
    )
}

