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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.brewco.ui.components.StockInputField
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.screens.inventory.StockViewModel

@Composable
fun EditCustomerScreen(
    navHostController: NavHostController,
    customerId: String,
    viewModel: CustomerViewModel = viewModel()
) {

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var ultCompra by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(0) }
    var correo by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    var isErrorVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Cargar el cliente desde la base de datos por el id
    LaunchedEffect(customerId) {
        viewModel.getClienteById(customerId) { fetchedClient ->
            fetchedClient?.let {
                nombre = it.nombre
                apellido = it.apellido
                telefono = it.telefono
                ultCompra = it.ultCompra
                puntos = it.puntos
                correo = it.correo
                notas = it.notas
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Editar Cliente",
                text1 = "Cancelar",
                text2 = "Guardar",
                navController = navHostController,
                onActionClick = {
                    // Validación de campos vacíos o valores inválidos
                    if (nombre.isBlank() || apellido.isBlank() || telefono.isBlank() || puntos < 0) {
                        errorMessage = "Por favor, rellena todos los campos."
                        isErrorVisible = true

                    } else {
                        val updatedClient = Client(
                            id = customerId,
                            nombre = nombre,
                            apellido = apellido,
                            telefono = telefono,
                            ultCompra = ultCompra,
                            puntos = puntos,
                            correo = correo,
                            notas = notas
                        )

                        // Actualizar el cliente en la base de datos
                        viewModel.updateClient(
                            client = updatedClient,
                            onSuccess = {
                                navHostController.navigate("customerScreen?edited=true") {
                                    popUpTo("editCustomerScreen") { inclusive = true }
                                }
                            },
                            onError = {
                                errorMessage = "Error al actualizar los datos del cliente"
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
                        onValueChange = { nombre = it }
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

                    CustomButton(
                        text = "Eliminar Cliente",
                        textColor = Color.Red,
                        contColor = Color.White,
                        fontSize = 16.sp,
                        contentPadding = PaddingValues(0.dp),
                        onClick = {

                        }
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
    )
}