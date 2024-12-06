package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.ImagePicker
import com.example.brewco.ui.components.StockInputField
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.screens.inventory.CustomerViewModel

@Composable
fun AddCustomerScreen(
    navHostController: NavHostController,
    viewModel: CustomerViewModel = viewModel()
) {
    // Variables para los campos de texto
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(0) }
    var ultCompra by remember { mutableStateOf("") }
    var nota by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Nuevo Cliente",
                text1 = "Cancelar",
                text2 = "Añadir",
                navController = navHostController, // Aquí se pasa el navHostController
                onActionClick = {


                })
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