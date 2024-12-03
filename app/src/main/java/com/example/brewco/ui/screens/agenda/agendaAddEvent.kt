package com.example.brewco.ui.screens.agenda

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.brewco.R
import com.example.brewco.R.*
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.components.*
import com.example.brewco.ui.theme.DarkBrown
import android.app.DatePickerDialog
import androidx.compose.runtime.*
import java.util.Calendar
import com.example.brewco.data.model.Alert
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaAddEvent(
    navHostController: NavHostController,
    selectedDate: String,
    viewModel: AlertViewModel = viewModel(),
    isAllDay: Boolean,
) {

    // Convertir selectedDate a formato dd/MM/yyyy
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inputFormat.parse(selectedDate) // Convertir la fecha de String a Date
    val formattedDate =
        if (date != null) outputFormat.format(date) else selectedDate // Formatear a dd/MM/yyyy

    var selectedFinalDate by remember { mutableStateOf(formattedDate) }
    var titulo by remember { mutableStateOf("") }
    var expandedInicialHour by remember { mutableStateOf(false) }
    var expandedInicialMinute by remember { mutableStateOf(false) }
    var expandedFinalHour by remember { mutableStateOf(false) }
    var expandedFinalMinute by remember { mutableStateOf(false) }
    var selectedInicicalHour by remember { mutableStateOf("00") }
    var selectedInicicalMinute by remember { mutableStateOf("00") }
    var selectedFinalMinute by remember { mutableStateOf("00") }
    var selectedFinalHour by remember { mutableStateOf("00") }
    var selectedOption by remember { mutableStateOf("No") }
    var expandedOption by remember { mutableStateOf(false) }
    var selectedEmployee by remember { mutableStateOf("Juan") }
    var expandedEmployee by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    // Usamos la variable isAllDay para determinar si es todo el día
    var isAllDayState by remember { mutableStateOf(isAllDay) }

    // Variables para mostrar mensajes de error
    var isErrorVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


// Mostrar DatePickerDialog cuando `showDatePicker` sea true
    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            LocalContext.current,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Si el usuario selecciona una fecha
                selectedFinalDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                showDatePicker = false // Ocultar el DatePicker
            },
            year,
            month,
            day
        ).apply {
            setOnDismissListener {
                // Se ejecuta cuando el DatePickerDialog se cierra (sea por cancelación o selección)
                showDatePicker = false
            }
        }.show()
    }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Nuevo Evento",
                text1 = "Cancelar",
                text2 = "Añadir",
                navController = navHostController, // Aquí se pasa el navHostController
                onActionClick = {
                    // Crear el objeto Alerta con los datos del formulario

                    if (isAllDayState) {
                        selectedFinalDate = formattedDate
                        selectedInicicalHour = "00"
                        selectedInicicalMinute = "00"
                        selectedFinalHour = "23"
                        selectedFinalMinute = "59"
                    }

                    val newAlert = Alert(
                        titulo = titulo,
                        fechaInicio = formattedDate,
                        horaInicio = selectedInicicalHour,
                        minutosInicio = selectedInicicalMinute,
                        fechaFin = selectedFinalDate,
                        horaFin = selectedFinalHour,
                        minutosFin = selectedFinalMinute,
                        aviso = selectedOption == "Si", // Convierte el String a Boolean
                        empleado = selectedEmployee,
                        descripcion = notes
                    )

                    if (newAlert.titulo.isBlank() || newAlert.fechaInicio.isBlank() || newAlert.horaInicio.isBlank() ||
                        newAlert.minutosInicio.isBlank() || newAlert.fechaFin.isBlank() || newAlert.horaFin.isBlank() ||
                        newAlert.minutosFin.isBlank() || newAlert.empleado.isBlank() || newAlert.descripcion.isBlank()
                    ) {
                        // Configura el mensaje de error y muestra el Snackbar
                        errorMessage = "Por favor, rellena todos los campos."
                        isErrorVisible = true

                    } else {

                        viewModel.addAlert(
                            alert = newAlert,
                            onSuccess = {
                                // Cuando alert se agrega correctamente, navega y pasa un argumento
                                navHostController.navigate("agendaScreen?added=true") {
                                    popUpTo("agendaScreen") { inclusive = true }
                                }

                            },
                            onError = { }
                        )

                    }

                }
            )
        },
        containerColor = Color.White,
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 32.dp,
                            bottom = 16.dp
                        ),
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

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Todo el día", style = TextStyle(color = DarkBrown))

                                // Aquí se cambia el valor de `isAllDayState` cuando se interactúa con el ToggleSwitch
                                ToggleSwitch(
                                    checked = isAllDayState,
                                    onCheckedChange = { isChecked ->
                                        isAllDayState = isChecked

                                        println("isAllDayState: $isAllDayState")

                                        if (isAllDayState) {
                                            showDatePicker = false
                                            selectedFinalMinute = "59"
                                            selectedFinalHour = "23"
                                        } else {
                                            selectedFinalMinute = "00"
                                            selectedFinalHour = "00"
                                        }
                                    }
                                )
                            }


                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.width(80.dp) // Define el ancho fijo del Row
                                ) {
                                    Text(
                                        "Empieza",
                                        style = TextStyle(color = DarkBrown),
                                        modifier = Modifier.fillMaxWidth() // Hace que el texto ocupe todo el ancho disponible
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                // Selector de fecha
                                Box(
                                    modifier = Modifier
                                        .background(color = Beige, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                ) {
                                    Text(formattedDate, style = TextStyle(color = DarkBrown))
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                // Selector de hora
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Beige,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                            .clickable { expandedInicialHour = true }
                                    ) {
                                        Text(
                                            "Hora: $selectedInicicalHour",
                                            style = TextStyle(color = DarkBrown)
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expandedInicialHour,
                                        onDismissRequest = { expandedInicialHour = false },
                                        modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                    ) {
                                        (0..23).forEach { hour ->
                                            DropdownMenuItem(
                                                text = { Text(hour.toString().padStart(2, '0')) },
                                                onClick = {
                                                    selectedInicicalHour =
                                                        hour.toString().padStart(2, '0')
                                                    expandedInicialHour = false
                                                }
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                // Selector de minutos
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Beige,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                            .clickable { expandedInicialMinute = true }
                                    ) {
                                        Text(
                                            "Minuto: $selectedInicicalMinute",
                                            style = TextStyle(color = DarkBrown)
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expandedInicialMinute,
                                        onDismissRequest = { expandedInicialMinute = false },
                                        modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                    ) {
                                        (0..59).forEach { minute ->
                                            DropdownMenuItem(
                                                text = { Text(minute.toString().padStart(2, '0')) },
                                                onClick = {
                                                    selectedInicicalMinute =
                                                        minute.toString().padStart(2, '0')
                                                    expandedInicialMinute = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.width(80.dp) // Define el ancho fijo del Row
                                ) {
                                    Text(
                                        "Termina",
                                        style = TextStyle(color = DarkBrown),
                                        modifier = Modifier.fillMaxWidth() // Hace que el texto ocupe todo el ancho disponible
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                // Selector de fecha
                                Box(
                                    modifier = Modifier
                                        .background(color = Beige, shape = RoundedCornerShape(8.dp))
                                        .padding(8.dp)
                                        .clickable(enabled = !isAllDayState) {
                                            showDatePicker = true
                                        } // Muestra el selector de fecha
                                ) {
                                    Text(selectedFinalDate, style = TextStyle(color = DarkBrown))
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                // Selector de hora
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Beige,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                            .clickable(enabled = !isAllDayState) {  // Solo habilitado si no es 'todo el día'
                                                expandedFinalHour = true
                                            }
                                    ) {
                                        Text(
                                            "Hora: $selectedFinalHour",
                                            style = TextStyle(color = DarkBrown)
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expandedFinalHour,
                                        onDismissRequest = { expandedFinalHour = false },
                                        modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                    ) {
                                        (0..23).forEach { hour ->
                                            DropdownMenuItem(
                                                text = { Text(hour.toString().padStart(2, '0')) },
                                                onClick = {
                                                    selectedFinalHour =
                                                        hour.toString().padStart(2, '0')
                                                    expandedFinalHour = false
                                                }
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                // Selector de minutos
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Beige,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                            .clickable(enabled = !isAllDayState) {  // Solo habilitado si no es 'todo el día'
                                                expandedFinalMinute = true
                                            }
                                    ) {
                                        Text(
                                            "Minuto: $selectedFinalMinute",
                                            style = TextStyle(color = DarkBrown)
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expandedFinalMinute,
                                        onDismissRequest = { expandedFinalMinute = false },
                                        modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                    ) {
                                        (0..59).forEach { minute ->
                                            DropdownMenuItem(
                                                text = { Text(minute.toString().padStart(2, '0')) },
                                                onClick = {
                                                    selectedFinalMinute =
                                                        minute.toString().padStart(2, '0')
                                                    expandedFinalMinute = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Aviso", style = TextStyle(color = DarkBrown))

                                // Selector de aviso
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Beige,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                            .clickable {
                                                expandedOption = !expandedOption
                                            } // Alterna la expansión al hacer clic
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(6.dp), // Espacio entre texto e icono
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "$selectedOption",
                                                style = TextStyle(color = DarkBrown)
                                            )
                                            Image(
                                                painter = painterResource(id = R.drawable.mynauichevronupdownsolid), // Usa el ID correcto
                                                contentDescription = "Flecha arriba/abajo",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.size(20.dp) // Ajusta el tamaño del icono
                                            )
                                        }
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
                                                    selectedOption =
                                                        option // Actualiza la opción seleccionada
                                                    expandedOption = false
                                                }
                                            )
                                        }
                                    }

                                }

                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Empleado", style = TextStyle(color = DarkBrown))


                                Column {

                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = Beige,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                            .clickable { expandedEmployee = !expandedEmployee }
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween, // Texto e icono separados
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "$selectedEmployee",
                                                style = TextStyle(color = DarkBrown)
                                            )
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Image(
                                                painter = painterResource(id = drawable.mynauichevronupdownsolid), // Reemplaza con el nombre correcto
                                                contentDescription = "Flecha arriba/abajo",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.size(20.dp) // Ajusta el tamaño del icono según sea necesario
                                            )
                                        }
                                    }

                                    DropdownMenu(
                                        expanded = expandedEmployee,
                                        onDismissRequest = { expandedEmployee = false },
                                        modifier = Modifier.heightIn(max = 200.dp) // Altura máxima del menú
                                    ) {
                                        listOf(
                                            "Pedro",
                                            "Juan",
                                            "María",
                                            "Jesús",
                                            "Mohamed"
                                        ).forEach { employee ->
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

                            Spacer(modifier = Modifier.height(20.dp))

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
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
                                        .border(
                                            1.dp,
                                            Beige,
                                            shape = RoundedCornerShape(8.dp)
                                        ), // Borde beige
                                    placeholder = { Text("Escribe tus notas aquí...") },
                                    textStyle = TextStyle(color = DarkBrown), // Color del texto
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Beige, // Fondo beige
                                        focusedIndicatorColor = Color.Transparent, // Sin línea de foco
                                        unfocusedIndicatorColor = Color.Transparent // Sin línea sin foco
                                    )
                                )


                            }
                        }
                    }
                }
                // Snackbar de error (superpuesto en la parte inferior)
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter // Posiciona el Snackbar en la parte inferior
                ) {
                    ErrorSnackbar(
                        message = errorMessage,
                        isVisible = isErrorVisible,
                        onDismiss = { isErrorVisible = false }
                    )
                }
            }
        }
    )
}




