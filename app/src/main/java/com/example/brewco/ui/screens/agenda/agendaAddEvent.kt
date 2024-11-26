package com.example.brewco.ui.screens.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.CustomBottomNavBar
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.components.*
import com.example.brewco.ui.theme.DarkBrown
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun AgendaAddEvent(navHostController: NavHostController, selectedDate: String) {
    var titulo by remember { mutableStateOf("") }
    var expandedHour by remember { mutableStateOf(false) }
    var expandedMinute by remember { mutableStateOf(false) }
    var selectedHour by remember { mutableStateOf("00") }
    var selectedMinute by remember { mutableStateOf("00") }
    var selectedOption by remember { mutableStateOf("Sí") }
    var expandedOption by remember { mutableStateOf(false) }
    var selectedEmployee by remember { mutableStateOf("Empleado 1") }
    var expandedEmployee by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Nuevo Evento",
                text1 = "Cancelar",
                text2 = "Añadir",
                onActionClick = { /* Lógica para añadir un evento */ }
            )
        },
        bottomBar = { CustomBottomNavBar(navHostController) },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Campo de texto para el título
                        CustomTextField(
                            value = titulo,
                            labelText = "Título",
                            onValueChange = { titulo = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Empieza", style = TextStyle(color = Brown))

                            // Selector de fecha
                            Box(
                                modifier = Modifier
                                    .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Text(selectedDate, style = TextStyle(color = DarkBrown))
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Selector de hora
                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable { expandedHour = true }
                                ) {
                                    Text(
                                        "Hora: $selectedHour",
                                        style = TextStyle(color = DarkBrown)
                                    )
                                }
                                DropdownMenu(
                                    expanded = expandedHour,
                                    onDismissRequest = { expandedHour = false },
                                    modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                ) {
                                    (0..23).forEach { hour ->
                                        DropdownMenuItem(
                                            text = { Text(hour.toString().padStart(2, '0')) },
                                            onClick = {
                                                selectedHour = hour.toString().padStart(2, '0')
                                                expandedHour = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Selector de minutos
                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable { expandedMinute = true }
                                ) {
                                    Text(
                                        "Minuto: $selectedMinute",
                                        style = TextStyle(color = DarkBrown)
                                    )
                                }
                                DropdownMenu(
                                    expanded = expandedMinute,
                                    onDismissRequest = { expandedMinute = false },
                                    modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                ) {
                                    (0..59).forEach { minute ->
                                        DropdownMenuItem(
                                            text = { Text(minute.toString().padStart(2, '0')) },
                                            onClick = {
                                                selectedMinute = minute.toString().padStart(2, '0')
                                                expandedMinute = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Termina", style = TextStyle(color = Brown))

                            // Selector de fecha
                            Box(
                                modifier = Modifier
                                    .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Text(selectedDate, style = TextStyle(color = DarkBrown))
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Selector de hora
                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable { expandedHour = true }
                                ) {
                                    Text(
                                        "Hora: $selectedHour",
                                        style = TextStyle(color = DarkBrown)
                                    )
                                }
                                DropdownMenu(
                                    expanded = expandedHour,
                                    onDismissRequest = { expandedHour = false },
                                    modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                ) {
                                    (0..23).forEach { hour ->
                                        DropdownMenuItem(
                                            text = { Text(hour.toString().padStart(2, '0')) },
                                            onClick = {
                                                selectedHour = hour.toString().padStart(2, '0')
                                                expandedHour = false
                                            }
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            // Selector de minutos
                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable { expandedMinute = true }
                                ) {
                                    Text(
                                        "Minuto: $selectedMinute",
                                        style = TextStyle(color = DarkBrown)
                                    )
                                }
                                DropdownMenu(
                                    expanded = expandedMinute,
                                    onDismissRequest = { expandedMinute = false },
                                    modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                ) {
                                    (0..59).forEach { minute ->
                                        DropdownMenuItem(
                                            text = { Text(minute.toString().padStart(2, '0')) },
                                            onClick = {
                                                selectedMinute = minute.toString().padStart(2, '0')
                                                expandedMinute = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Aviso", style = TextStyle(color = Brown))


                            // Selector de aviso
                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable { expandedOption = true }
                                ) {
                                    Text(
                                        "Aviso: $selectedOption",
                                        style = TextStyle(color = DarkBrown)
                                    )
                                }

                                DropdownMenu(
                                    expanded = expandedOption,
                                    onDismissRequest = { expandedOption = false },
                                    modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                ) {
                                    listOf("No", "Si").forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option) },
                                            onClick = {
                                                selectedOption = option
                                                expandedOption = false
                                            }
                                        )
                                    }
                                }
                            }

                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Empleado", style = TextStyle(color = Brown))


                            Column {
                                Box(
                                    modifier = Modifier
                                        .background(color = Brown, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable { expandedEmployee = true }
                                ) {
                                    Text(
                                        "Empleado: $selectedEmployee",
                                        style = TextStyle(color = DarkBrown)
                                    )
                                }

                                DropdownMenu(
                                    expanded = expandedEmployee,
                                    onDismissRequest = { expandedEmployee = false },
                                    modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                ) {
                                    listOf("Pedro", "Juan", "María", "Jesús", "Mohamed").forEach { employee ->
                                        DropdownMenuItem(
                                            text = { Text(employee) },
                                            onClick = {
                                                selectedEmployee = employee
                                                expandedEmployee = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Notas",
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            TextField(
                                value = notes,
                                onValueChange = { notes = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp) // Altura para simular un TextArea
                                    .background(Brown, shape = RoundedCornerShape(8.dp))
                                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp)),
                                placeholder = { Text("Escribe tus notas aquí...") },
                                textStyle = TextStyle(color = Color.Black)
                            )
                        }
                    }
                }
            }
        }
    )
}



