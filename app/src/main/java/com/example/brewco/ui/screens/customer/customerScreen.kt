package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomDrawer
import com.example.brewco.ui.components.CustomFloatingActionButton
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.theme.*
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
                        popUpTo(0)
                    }
                }
            )
        },
    ) { }
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
                    items(10) { index ->
                        CustomerItem(index)
                    }
                }
            }
        }
    )
}



@Composable
fun CustomerItem(index: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).height(150.dp),

        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))

    ) {
    Column(
        modifier = Modifier
            .border(
                width = 2.dp, color = Brown
            )
            .fillMaxSize()
    ){
        Text(
            text = "Nombre Apellido Apellidos",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .border(
                    width = 2.dp, color = Brown
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
                text = "1",
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
                    width = 2.dp, color = Brown
                )
                .fillMaxWidth()
        ) {
            Text(
                text = "Tel: ",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Text(
                text = "Variable",
                modifier = Modifier
            )
        }
        Column(
            modifier = Modifier
                .border(
                    width = 2.dp, color = Brown
                )
                .fillMaxWidth()
        ) {
            Text(
                text = "Últ Compra: ",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Text(
                text = "20/12/2020",
                modifier = Modifier
            )
        }
    }

    }
}
