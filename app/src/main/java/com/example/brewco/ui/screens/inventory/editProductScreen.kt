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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomButton
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.ImagePicker
import com.example.brewco.ui.components.StockInputField
import com.example.brewco.ui.components.TopBarWithText

@Composable
fun EditProductScreen(navHostController: NavHostController, productId: String, viewModel: StockViewModel = viewModel()) {
    // Estado mutable para los campos de texto
    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var stockDisponible by remember { mutableStateOf(0) }
    var stockMinimo by remember { mutableStateOf(0) }
    var precio by remember { mutableStateOf(0) }

    // Cargar el producto desde la base de datos por el id
    LaunchedEffect(productId) {
        viewModel.getProductById(productId) { fetchedProduct ->
            fetchedProduct?.let {
                nombre = it.nombre
                categoria = it.categoria
                stockDisponible = it.inventario
                stockMinimo = it.inventario_minimo
                precio = it.precio
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Editar Producto",
                text1 = "Cancelar",
                text2 = "Guardar",
                onActionClick = {
                    // Verificar que los campos no estén vacíos o con valores inválidos
                    if (nombre.isBlank() || categoria.isBlank() ||
                        stockDisponible < 0 || stockMinimo < 0 || precio <= 0.0) {
                        //Recuerda agregar un Texto de aviso
                    } else {
                        val updatedProduct = Product(
                            id = productId,
                            nombre = nombre,
                            categoria = categoria,
                            inventario = stockDisponible,
                            inventario_minimo = stockMinimo,
                            precio = precio
                        )

                        // Actualizar el producto en la base de datos
                        viewModel.updateProduct(
                            product = updatedProduct,
                            onSuccess = {
                                navHostController.navigate("inventoryScreen?edited=true") {
                                    popUpTo("editProductScreen") { inclusive = true }
                                }
                            },
                            onError = {
                            }
                        )
                    }
                }
            )
        },
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
                    Spacer(modifier = Modifier.height(24.dp))
                    CustomTextField(
                        value = nombre,
                        labelText = "Producto",
                        onValueChange = { nombre = it }
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    CustomTextField(
                        value = categoria,
                        labelText = "Categoría",
                        onValueChange = { categoria = it }
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    StockInputField(
                        "Stock disponible",
                        value = stockDisponible,
                        onValueChange = { newValue -> stockDisponible = newValue }
                    )
                    StockInputField(
                        "Stock mínimo",
                        value = stockMinimo,
                        onValueChange = { newValue -> stockMinimo = newValue }
                    )
                    StockInputField(
                        "Precio",
                        value = precio,
                        onValueChange = { newValue -> precio = newValue }
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    CustomButton(
                        text = "Eliminar producto",
                        onClick = {
                            viewModel.deleteProduct(
                                productId = productId,
                                onSuccess = {
                                    navHostController.navigate("inventoryScreen?delete=true") {
                                        popUpTo("editProductScreen") { inclusive = true }
                                    }
                                },
                                onError = { errorMessage ->
                                }
                            )
                        }
                    )
                }
            }
        }
    )
}

