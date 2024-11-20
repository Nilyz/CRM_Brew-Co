package com.example.brewco.ui.screens.agenda

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun AgendaAddEvent(navHostController: NavHostController, selectedDate: String) {
    // Mostrar la fecha seleccionada
    Text("Fecha seleccionada: $selectedDate")

    // Aquí puedes agregar más lógica para el formulario de eventos o cualquier cosa que necesites hacer
}
