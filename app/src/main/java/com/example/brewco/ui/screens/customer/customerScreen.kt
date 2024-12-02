package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomDrawer
import com.example.brewco.ui.components.CustomFloatingActionButton
import com.example.brewco.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun CustomerScreen(navHostController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawer(
                navHostController = navHostController,
                onLogoutClick = {
                    navHostController.navigate("splashScreen") {
                        popUpTo(0) // Limpia la pila de navegación
                    }
                } )
        },
    ) { Scaffold(
        topBar = { TopBar(title = "Clientes", onMenuClick ={
            scope.launch {
                if (drawerState.isClosed) {
                    drawerState.open()
                } else {
                    drawerState.close()
                }
            }
        }
        ) },
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Simulamos contenido
                    items(50) { index ->
                        CustomerItem(index)
                    }
                }
            }
        }
    )}

}


@Composable
fun CustomerItem(index: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .height(300.dp)
                    .padding(8.dp)
            ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .height(300.dp)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
                ) {
                    Text(
                        text = "Evento $index",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(16.dp),
                    )
                    Text(
                        text = "Puntos: 250",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp),
                    )
                    Text(
                        text = "Teléfono: 123456789",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp),
                    )
                    Text(
                        text = "Última compra: 12/12/2021",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp),
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .height(300.dp)
                    .padding(8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.35f)
                        .height(300.dp)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
                ) {
                    Text(
                        text = "Evento $index",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(16.dp),
                    )
                    Text(
                        text = "Puntos: 250",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp),
                    )
                    Text(
                        text = "Teléfono: 123456789",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp),
                    )
                    Text(
                        text = "Última compra: 12/12/2021",
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(8.dp),
                    )
                }

            }
        }

}
