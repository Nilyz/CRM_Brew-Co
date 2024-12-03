package com.example.brewco.ui.screens.agenda

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.LazyColumn
import com.example.brewco.ui.components.TopBarWithText
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.ToggleSwitch
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.DarkBrown
import androidx.compose.ui.Modifier
import java.util.Calendar
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ButtonDefaults



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaEditEvent(navHostController: NavHostController, alertId: String, isAllDay: Boolean, viewModel: AlertViewModel = viewModel()) {

    var id by remember { mutableStateOf("") }
    var titulo by remember { mutableStateOf("") }
    var fechaInicio by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var minutosInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var minutosFin by remember { mutableStateOf("") }
    var empleado by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var aviso by remember { mutableStateOf(false) }

    var expandedInicialHour by remember { mutableStateOf(false) }
    var expandedInicialMinute by remember { mutableStateOf(false) }
    var expandedFinalHour by remember { mutableStateOf(false) }
    var expandedFinalMinute by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("No") }
    var expandedOption by remember { mutableStateOf(false) }
    var expandedEmployee by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var initialChecked by remember { mutableStateOf(false) } // Estado inicial

    var isAllDayState by remember { mutableStateOf(isAllDay) }

    // Cargar datos
    LaunchedEffect(alertId) {
        viewModel.getAlertById(alertId) { fetchedProduct ->
            fetchedProduct?.let {
                id = it.id
                titulo = it.titulo
                fechaInicio = it.fechaInicio
                horaInicio = it.horaInicio
                minutosInicio = it.minutosInicio
                fechaFin = it.fechaFin
                horaFin = it.horaFin
                minutosFin = it.minutosFin
                aviso = it.aviso
                empleado = it.empleado
                descripcion = it.descripcion

                // Actualiza isAllDayState en función de los valores iniciales
                isAllDayState = horaInicio == "00" && minutosInicio == "00" && horaFin == "23" && minutosFin == "59"
            }
        }
    }

    selectedOption = if (aviso) "Si" else "No"

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
                fechaFin = "$selectedDay/${selectedMonth + 1}/$selectedYear"
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
                title = "Editar Evento",
                text1 = "Cancelar",
                text2 = "OK",
                navController = navHostController, // Aquí se pasa el navHostController
                onActionClick = {

                    // Crear el objeto Alerta con los datos del formulario

                    if (isAllDayState) {
                        //fechaFin = formattedDate
                        horaInicio = "00"
                        minutosInicio = "00"
                        horaFin = "23"
                        minutosFin = "59"
                    }

                    val newAlert = Alert(
                        id = id,
                        titulo = titulo,
                        fechaInicio = fechaInicio,
                        horaInicio = horaInicio,
                        minutosInicio = minutosInicio,
                        fechaFin = fechaFin,
                        horaFin = horaFin,
                        minutosFin = minutosFin,
                        aviso = selectedOption == "Si",
                        empleado = empleado,
                        descripcion = descripcion

                    )

                    if (newAlert.titulo.isBlank() || newAlert.fechaInicio.isBlank() || newAlert.horaInicio.isBlank() ||
                        newAlert.minutosInicio.isBlank() || newAlert.fechaFin.isBlank() || newAlert.horaFin.isBlank() ||
                        newAlert.minutosFin.isBlank() || newAlert.empleado.isBlank() || newAlert.descripcion.isBlank()
                    ) {
                        // Todo añadir mensaje que diga que rellenes todos los campos

                    } else {

                        viewModel.updateAlert(
                            alert = newAlert,
                            onSuccess = {
                                // Cuando la alerta se actualiza correctamente, navega y pasa un argumento
                                navHostController.navigate("agendaScreen?edited=true") {
                                    popUpTo("agendaViewEvent") { inclusive = true }
                                }
                            },
                            onError = { errorMessage ->
                                // Muestra el mensaje de error, si es necesario
                                println("Error: $errorMessage")
                            }
                        )
                    }
                }
            )

        },
        containerColor = Color.White, // Fondo blanco para toda la pantalla
        content = { paddingValues ->

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

                                    // Ajustar los valores de hora y minuto según el estado
                                    if (isAllDayState) {
                                        showDatePicker = false
                                        horaInicio = "00"
                                        minutosInicio = "00"
                                        horaFin = "23"
                                        minutosFin = "59"
                                    } else {
                                        minutosFin = "00"
                                        horaFin = "00"
                                    }

                                    println("isAllDayState: $isAllDayState")
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
                                Text(fechaInicio, style = TextStyle(color = DarkBrown))
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
                                        "Hora: $horaInicio",
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
                                                horaInicio =
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
                                        "Minuto: $minutosInicio",
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
                                                minutosInicio =
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
                                    .clickable (enabled = !isAllDayState) {
                                        showDatePicker = true
                                    } // Muestra el selector de fecha
                            ) {
                                Text(fechaFin, style = TextStyle(color = DarkBrown))
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
                                        "Hora: $horaFin",
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
                                                horaFin =
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
                                        "Minuto: $minutosFin",
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
                                                minutosFin =
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
                                            text = "$empleado",
                                            style = TextStyle(color = DarkBrown)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Image(
                                            painter = painterResource(id = R.drawable.mynauichevronupdownsolid), // Reemplaza con el nombre correcto
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
                                                empleado = employee
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
                                value = descripcion,
                                onValueChange = { descripcion = it },
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

                    Spacer(modifier = Modifier.height(20.dp))

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
                            .fillMaxWidth()
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
                            color = Color.Red // Texto en rojo
                        )
                    }
                }

            }

        }
    )
}
