package com.example.brewco.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.example.brewco.R
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Beige


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(title: String) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Menu, contentDescription = "Perfil")
            }
        }
    )
}

@Composable
fun CustomBottomBar(
    navController: NavController,
    onAgendaClick: () -> Unit = { navController.navigate("pantallaPacientesVer") },
    onCustomerClick: () -> Unit = { navController.navigate("customerScreen") },
    onHomeClick: () -> Unit = { navController.navigate("homeScreen") },
    onInventoryClick: () -> Unit = { navController.navigate("inventoryScreen") },
    onNotificationClick: () -> Unit = { navController.navigate("notificationScreen") },
    agendaIconColor: Color = Color.White, // Parámetro para el color del ícono Agenda
    customerIconColor: Color = Color.White, // Parámetro para el color del ícono Cliente
    homeIconColor: Color = Brown, // Color por defecto para Home (puedes cambiarlo dinámicamente)
    inventoryIconColor: Color = Color.White, // Parámetro para el color del ícono Inventario
    notificationIconColor: Color = Color.White // Parámetro para el color del ícono Notificaciones
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp), // Espacio debajo para separar de la parte inferior de la pantalla
        contentAlignment = Alignment.Center
    ) {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth(0.95f) // 95% del ancho total
                .clip(RoundedCornerShape(15.dp)) // Esquinas redondeadas
                .padding(vertical = 4.dp)
                .height(56.dp), // Establecer una altura más baja para la BottomAppBar
            containerColor = Beige , // Color de fondo gris claro
            contentColor = Color.Gray // Color de los iconos
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onAgendaClick) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Agenda",
                        tint = agendaIconColor, // Color dinámico
                        modifier = Modifier.size(36.dp) // Reducir tamaño de los íconos
                    )
                }
                IconButton(onClick = onCustomerClick) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Perfil Cliente",
                        tint = customerIconColor, // Color dinámico
                        modifier = Modifier.size(36.dp) // Reducir tamaño de los íconos
                    )
                }
                IconButton(onClick = onHomeClick) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = homeIconColor, // Color dinámico
                        modifier = Modifier.size(36.dp) // Reducir tamaño de los íconos
                    )
                }
                IconButton(onClick = onInventoryClick) {
                    // Cargar el icono SVG desde los recursos
                    Image(
                        painter = painterResource(id = R.drawable.bxsbox), // Ruta del archivo SVG
                        contentDescription = "Inventario", // Descripción del icono
                        modifier = Modifier.size(36.dp), // Reducir tamaño del icono
                        colorFilter = ColorFilter.tint(inventoryIconColor) // Aplica el color dinámico
                    )
                }
                IconButton(onClick = onNotificationClick) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificaciones",
                        tint = notificationIconColor, // Color dinámico
                        modifier = Modifier.size(36.dp) // Reducir tamaño de los íconos
                    )
                }
            }
        }
    }
}



