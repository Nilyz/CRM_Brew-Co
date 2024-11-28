package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.material3.Text

@Composable
fun CustomerDetailsScreen(
    navHostController: NavHostController,
    clientId: String,
    viewModel: CustomerViewModel = viewModel()
) {

    Column() {
        Column() {
            Text(
                clientId
            )

        }
        Column() {

        }
    }

}