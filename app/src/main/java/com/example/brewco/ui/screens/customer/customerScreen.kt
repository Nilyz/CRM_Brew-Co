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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.brewco.ui.screens.inventory.StockViewModel

@Composable
fun CustomerScreen(
    navHostController: NavHostController,
    viewModel: CustomerViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val clientList by viewModel.customers.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawer(
                navHostController = navHostController,
                onLogoutClick = {
                    navHostController.navigate("splashScreen") {
                        popUpTo(0)
                    }
                }
            )
        },
    ) {
        Scaffold(
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
                CustomFloatingActionButton(navHostController)
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(clientList) { client ->
                            CustomerItem(client = client, navHostController = navHostController)
                            Spacer(modifier = Modifier.height(16.dp))

                        }
                    }
                }
            }
        )
    }

}


@Composable
fun CustomerItem(client: Client, navHostController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(180.dp)
            .clickable {
                navHostController.navigate("customerDetailsScreen/${client.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Beige)

    ) {

        Column(
            modifier = Modifier
                .border(
                    width = 2.dp, color = Color.Transparent
                )
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = "${client.nombre} ${client.apellido}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp, Color.Transparent
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Puntos: ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier

                )
                Text(
                    text = "${client.puntos}",
                    modifier = Modifier
                )
                Icon(
                    Icons.Default.Star, contentDescription = "Agregar",
                    modifier = Modifier.size(20.dp),
                    Color.Yellow
                )
            }
            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp, Color.Transparent
                    )
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .border(
                            width = 2.dp, Color.Transparent
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Tel:",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                    Text(
                        text = "${client.telefono}",
                        modifier = Modifier
                    )
                }
            }
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp, Color.Transparent
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Últ Compra: ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Text(
                    text = "${client.ultCompra}",
                    modifier = Modifier
                )
            }

        }
    }

}