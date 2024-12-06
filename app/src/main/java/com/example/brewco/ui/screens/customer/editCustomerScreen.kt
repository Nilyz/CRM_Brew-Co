package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.screens.inventory.CustomerViewModel

@Composable
fun EditCustomerScreen(
    navHostController: NavHostController,
    customerid: String,
    viewModel: CustomerViewModel = viewModel()
) {
    // Variables que se actualizarán con los datos de la base de datos
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf("") }
    var ultCompra by remember { mutableStateOf("") }

    // Efecto para cargar los datos desde la base de datos al abrir la pantalla
    LaunchedEffect(customerid) {
        viewModel.getCustomerById(customerid = id) { fetchedElement ->
            fetchedElement?.let {
                // Actualiza las variables con los valores obtenidos
                nombre = it.nombre
                apellido = it.apellido
                telefono = it.telefono
                correo = it.correo
                puntos = it.puntos
                ultCompra = it.ultCompra
            }
        }
    }

    // Interfaz de usuario
    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Editar Cliente",
                text1 = "Cancelar",
                text2 = "Guardar",
                navController = navHostController,
                onActionClick = {
                    // Acción al guardar
                }
            )
        },
        containerColor = Color.White,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Campos de texto personalizados para cada dato
                    CustomTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        labelText = "Nombre"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    CustomTextField(
                        value = apellido,
                        onValueChange = { apellido = it }, // Cambiar aquí
                        labelText = "Apellido"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    CustomTextField(
                        value = telefono,
                        onValueChange = { telefono = it }, // Cambiar aquí
                        labelText = "Telefono"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    CustomTextField(
                        value = correo,
                        onValueChange = { correo = it }, // Cambiar aquí
                        labelText = "Correo"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    CustomTextField(
                        value = puntos,
                        onValueChange = { puntos = it }, // Cambiar aquí
                        labelText = "Puntos"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    CustomTextField(
                        value = ultCompra,
                        onValueChange = { ultCompra = it }, // Cambiar aquí
                        labelText = "Ultima Compra"
                    )
                }
            }
        }
    )
}