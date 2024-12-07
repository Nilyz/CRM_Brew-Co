package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Client
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomButton
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.ErrorSnackbar
import com.example.brewco.ui.components.StockInputField
import com.example.brewco.ui.components.TopBarWithText
import com.example.brewco.ui.screens.inventory.StockViewModel
import com.example.brewco.ui.theme.DarkBrown

@Composable
fun EditCustomerScreen(
    navHostController: NavHostController,
    clientId: String,
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

    // Función para validar el correo electrónico
    fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        return email.matches(emailPattern.toRegex())
    }

    // Cargar el cliente desde la base de datos por el id
    LaunchedEffect(clientId) {
        viewModel.getClienteById(clientId) { fetchedClient ->
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

                    } else if (!isValidEmail(correo)) {
                        errorMessage = "Por favor, ingresa un correo electrónico válido."
                        isErrorVisible = true
                    } else {
                        val updatedClient = Client(
                            id = clientId,
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
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Campo de texto para el nombre
                        CustomTextField(
                            value = nombre,
                            labelText = "Nombre",
                            onValueChange = { nombre = it },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Campo de texto para el apellido
                        CustomTextField(
                            value = apellido,
                            labelText = "Apellido",
                            onValueChange = { apellido = it },
                            modifier = Modifier.fillMaxWidth()
                        )


                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.phone_svgrepo_com),
                                contentDescription = "Phone",
                                modifier = Modifier
                                    .size(45.dp)
                            )

                            CustomTextField(
                                value = telefono,
                                labelText = "+34",
                                onValueChange = { telefono = it },
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )

                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mail_svgrepo_com),
                                contentDescription = "Mail",
                                modifier = Modifier
                                    .size(45.dp)

                            )

                            CustomTextField(
                                value = correo,
                                labelText = "Correo",
                                onValueChange = { correo = it },
                                modifier = Modifier.fillMaxWidth(0.95f) // Ancho proporcional consistente
                            )
                        }


                        // Campo de texto para la última compra con icono
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = "Agenda",
                                modifier = Modifier
                                    .size(45.dp),

                                tint = DarkBrown
                            )

                            CustomTextField(
                                value = ultCompra,
                                labelText = "Última Compra",
                                onValueChange = { ultCompra = it },
                                modifier = Modifier.fillMaxWidth(0.95f)
                            )

                        }

                        // Campo de texto para los puntos con icono
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.present_svgrepo_com),
                                contentDescription = "Present",
                                modifier = Modifier
                                    .size(38.dp)
                            )

                            StockInputField(
                                "Puntos",
                                value = puntos,
                                onValueChange = { newValue -> puntos = newValue },
                                modifier = Modifier.fillMaxWidth(0.90f)


                            )

                        }

                        // Campo de texto para las notas
                        CustomTextField(
                            value = notas,
                            labelText = "Notas",
                            onValueChange = { notas = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    CustomButton(
                        text = "Eliminar Cliente",
                        textColor = Color.Red,
                        contColor = Color.White,
                        fontSize = 16.sp,
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            viewModel.deleteClient(
                                clientId = clientId,
                                onSuccess = {
                                    navHostController.navigate("customerScreen?delete=true") {
                                        popUpTo("customerDetailsScreen") { inclusive = true }
                                    }
                                },
                                onError = { errorMessage ->
                                    // Manejar el error, como mostrar un mensaje al usuario
                                }
                            )
                        }
                    )
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentAlignment = Alignment.BottomCenter
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