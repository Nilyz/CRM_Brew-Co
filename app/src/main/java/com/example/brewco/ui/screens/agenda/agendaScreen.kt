package com.example.brewco.ui.screens.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.components.CustomBottomNavBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.ZoneId
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.brewco.ui.components.CustomSnackBar
import com.example.brewco.ui.components.EventCard
import com.example.brewco.ui.components.SnackbarMessageHandler
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(navHostController: NavHostController, viewModel: AlertViewModel = viewModel()) {

    // Observa la lista de productos
    val eventList by viewModel.alerts.collectAsState()

    val datePickerState = rememberDatePickerState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val addedMessage = navHostController.currentBackStackEntry?.arguments?.getString("added")
    val deleteMessage = navHostController.currentBackStackEntry?.arguments?.getString("delete")
    val editedMessage = navHostController.currentBackStackEntry?.arguments?.getString("edited")



    LaunchedEffect(Unit) {
        viewModel.loadTodayAlerts()  //recargar productos cada vez que se entra a la pantalla
    }

    Scaffold(snackbarHost = {
        CustomSnackBar(snackbarHostState)
    },
        topBar = {
            TopBar(title = "Agenda", onMenuClick = {
                scope.launch {
                    if (drawerState.isClosed) {
                        drawerState.open()
                    } else {
                        drawerState.close()
                    }
                }

            })
        },
        bottomBar = { CustomBottomNavBar(navHostController) },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Elemento fijo: DatePicker
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MaterialTheme(
                            colorScheme = MaterialTheme.colorScheme.copy(
                                primary = Brown, // Color para el día seleccionado
                                onPrimary = DarkBrown // Color del texto del día seleccionado
                            )
                        ) {
                            DatePicker(
                                state = datePickerState,
                                showModeToggle = false
                            )
                        }

                    }
                }

                // Elemento fijo: Botón
                item {
                    Button(
                        onClick = {
                            val selectedDateMillis = datePickerState.selectedDateMillis
                            if (selectedDateMillis != null) {
                                val selectedDate = Instant.ofEpochMilli(selectedDateMillis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()

                                // Navega a la pantalla para añadir el evento
                                navHostController.navigate("agendaAddEvent/${selectedDate}")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Brown // Color de fondo del botón
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Agregar evento")
                    }
                }

                // Título: "Eventos de hoy"
                item {
                    Text(
                        text = "Eventos de hoy",
                        style = MaterialTheme.typography.titleMedium, // Título con estilo
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp), // Espaciado superior e inferior
                        color = DarkBrown // Usando el color principal del tema
                    )
                }

                items(eventList) { alert ->
                    EventCard(alert = alert, navHostController = navHostController)
                }

                // Espaciador al final
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
            SnackbarMessageHandler(
                navHostController = navHostController,
                snackbarHostState = snackbarHostState,
                element = "Evento"
            )
        }
    )
}





