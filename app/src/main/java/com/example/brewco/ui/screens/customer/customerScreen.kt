package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomDrawer
import com.example.brewco.ui.components.CustomFloatingActionButton
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.theme.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.Button
import com.example.brewco.data.model.Client
import com.example.brewco.ui.components.CustomSnackBar
import com.example.brewco.ui.components.CustomTextField
import com.example.brewco.ui.components.CustomerItem
import com.example.brewco.ui.components.SnackbarMessageHandler
import com.example.brewco.ui.screens.inventory.StockViewModel
import com.example.brewco.ui.screens.login.AuthViewModel

@Composable
fun CustomerScreen(
    navHostController: NavHostController,
    viewModel: CustomerViewModel = viewModel()
) {
    val authViewModel: AuthViewModel = viewModel()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Observar la lista completa de clientes
    val clientList by viewModel.customers.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val authState by authViewModel.authState.observeAsState()

    // Estado para el teléfono (búsqueda)
    var phoneNumber by remember { mutableStateOf("") }

    // Filtrar la lista de clientes en tiempo real
    val filteredClients = remember(phoneNumber, clientList) {
        if (phoneNumber.isEmpty()) clientList else viewModel.filterClientsByPhone(phoneNumber)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawer(
                navHostController = navHostController,
                authViewModel = authViewModel,
                onLogoutClick = {}
            )
        },
    ) {
        Scaffold(
            snackbarHost = {
                CustomSnackBar(snackbarHostState)
            },
            topBar = {
                TopBar(title = "Clientes", onMenuClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                })
            },
            containerColor = Color.White,
            bottomBar = { CustomBottomNavBar(navHostController) },
            floatingActionButton = {
                CustomFloatingActionButton(
                    navHostController,
                    onClick = { navHostController.navigate("addCustomerScreen") })
            },
            content = { paddingValues ->

                // Usamos un Box para manejar el contenido principal
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        // CustomTextField para ingresar el teléfono
                        CustomTextField(
                            value = phoneNumber,
                            labelText = "Introduce el teléfono",
                            onValueChange = { phoneNumber = it },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Mostrar un mensaje si no hay clientes
                        if (filteredClients.isEmpty()) {
                            Text(
                                text = "No hay clientes disponibles.",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        } else {
                            // Usamos LazyVerticalGrid para mostrar los clientes filtrados en una cuadrícula de 2 por fila
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2), // Definimos 2 columnas
                                modifier = Modifier
                                    .border(1.dp, Color.Black)
                                    .fillMaxSize(),
                                contentPadding = PaddingValues(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(filteredClients) { client ->
                                    CustomerItem(client = client, navHostController = navHostController)
                                }
                            }
                        }
                    }

                    // Añadimos el SnackbarMessageHandler al final
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        SnackbarMessageHandler(
                            navHostController = navHostController,
                            snackbarHostState = snackbarHostState,
                            element = "Cliente"
                        )
                    }
                }
            }
        )
    }
}






