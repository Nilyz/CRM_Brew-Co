package com.example.brewco.ui.screens.inventory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.*


@Composable
fun AddProductScreen(
    navHostController: NavHostController,
    viewModel: StockViewModel = viewModel()
) {
    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var stockDisponible by remember { mutableStateOf(0) }
    var stockMinimo by remember { mutableStateOf(0) }
    var precio by remember { mutableStateOf(0) }

    var isErrorVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Nuevo Producto",
                text1 = "Cancelar",
                text2 = "Añadir",
                navController = navHostController, // Aquí se pasa el navHostController
                onActionClick = {
                    val newProduct = Product(
                        nombre = nombre,
                        categoria = categoria,
                        inventario = stockDisponible,
                        inventario_minimo = stockMinimo,
                        precio = precio
                    )
                    if (newProduct.nombre.isBlank() || newProduct.categoria.isBlank() ||
                        newProduct.inventario <= 0 || newProduct.inventario_minimo <= 0 ||
                        newProduct.precio <= 0.0
                    ) {
                        errorMessage = "Por favor, rellena todos los campos."
                        isErrorVisible = true
                    } else {
                        viewModel.addProduct(
                            product = newProduct,
                            onSuccess = {
                                // Cuando el producto se agrega correctamente, navega y pasa un argumento
                                navHostController.navigate("inventoryScreen?added=true") {
                                    popUpTo("addProductScreen") { inclusive = true }
                                }

                            },
                            onError = {
                                errorMessage = "Hubo un problema al agregar el producto."
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
                    ImagePicker()
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomTextField(
                        value = nombre,
                        labelText = "Producto",
                        onValueChange = { nombre = it },
                        modifier=Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomTextField(
                        value = categoria,
                        labelText = "Categoría",
                        onValueChange = { categoria = it },
                        modifier=Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    StockInputField(
                        "Stock disponible",
                        value = stockDisponible,
                        onValueChange = { newValue -> stockDisponible = newValue },
                        modifier=Modifier.fillMaxWidth())
                    StockInputField(
                        "Stock mínimo",
                        value = stockMinimo,
                        onValueChange = { newValue -> stockMinimo = newValue },
                        modifier=Modifier.fillMaxWidth())
                    StockInputField(
                        "Precio",
                        value = precio,
                        onValueChange = { newValue -> precio = newValue },
                        modifier=Modifier.fillMaxWidth())

                }
            }
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


    )
}

