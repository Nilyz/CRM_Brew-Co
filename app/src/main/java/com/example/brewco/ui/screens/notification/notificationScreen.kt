package com.example.brewco.ui.screens.notification

import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Alert
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomDrawer
import com.example.brewco.ui.components.NotificationItem
import com.example.brewco.ui.components.ProductItem
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun NotificationScreen(
    navHostController: NavHostController,
    viewModel: NotificationViewModel = viewModel()
) {
    // Recogemos las alertas desde el ViewModel
    val alertList by viewModel.alerts.collectAsState()

    // Filtrar las alertas con aviso = true
    val filteredAlerts = alertList.filter { it.aviso==true }

    LaunchedEffect(Unit) {
        viewModel.getAlertByPrompt() // Esto ejecuta la función que obtiene las alertas
    }

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
                })
        },
    ) {}

    Scaffold(
        topBar = {
            TopBar(title = "Notificaciones", onMenuClick = {
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
                    // Mostrar las alertas filtradas
                    items(filteredAlerts) { alert ->
                        NotificationItem(alert)
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Espaciado adicional al final
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    )
}

