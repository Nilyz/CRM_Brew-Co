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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.ImagePicker
import com.example.brewco.ui.components.StockInputField
import com.example.brewco.ui.components.TopBarWithText

@Composable
fun AddCustomerScreen(navHostController: NavHostController) {
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

                }
            }
        }
    )
}