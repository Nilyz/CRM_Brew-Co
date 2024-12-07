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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Client
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomButton
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.ErrorSnackbar
import com.example.brewco.ui.components.ImagePicker
import com.example.brewco.ui.components.StockInputField
import com.example.brewco.ui.components.TopBarWithText

@Composable
fun AddCustomerScreen(
    navHostController: NavHostController,
    viewModel: CustomerViewModel = viewModel()
) {

    // Estado mutable para los campos de texto
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ultCompra by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(0) }
    var correo by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    var isErrorVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Nuevo Cliente",
                text1 = "Cancelar",
                text2 = "Añadir",
                navController = navHostController, // Aquí se pasa el navHostController
                onActionClick = {
                    val newClient = Client(
                        nombre = nombre,
                        apellido = apellido,
                        telefono = telefono,
                        ultCompra = ultCompra,
                        puntos = puntos,
                        correo = correo,
                        notas = notas
                    )

                    // Validación de los campos
                    if (newClient.nombre.isBlank() || newClient.apellido.isBlank() || newClient.telefono.isBlank() ||
                        newClient.correo.isBlank() || newClient.ultCompra.isBlank()
                    ) {
                        errorMessage = "Por favor, rellena todos los campos."
                        isErrorVisible = true // Se muestra el error si hay campos vacíos
                    } else {

                        viewModel.addClient(
                            client = newClient,
                            onSuccess = {
                                navHostController.navigate("customerScreen?added=true") {
                                    popUpTo("addCustomerScreen") { inclusive = true }
                                }
                            },
                            onError = {

                                errorMessage = "Hubo un problema al agregar el cliente."
                                isErrorVisible = true
                            }
                        )
                    }

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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        CustomTextField(
                            value = nombre,
                            labelText = "nombre",
                            onValueChange = { nombre = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomTextField(
                            value = apellido,
                            labelText = "apellido",
                            onValueChange = { apellido = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomTextField(
                            value = telefono,
                            labelText = "+34 ",
                            onValueChange = { telefono = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomTextField(
                            value = correo,
                            labelText = "correo ",
                            onValueChange = { correo = it }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomTextField(
                            value = ultCompra,
                            labelText = "última compra ",
                            onValueChange = { ultCompra = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        StockInputField(
                            "Puntos",
                            value = puntos,
                            onValueChange = { newValue -> puntos = newValue }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CustomTextField(
                            value = notas,
                            labelText = "notas ",
                            onValueChange = { notas = it }
                        )



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
            }
        }
    )
}