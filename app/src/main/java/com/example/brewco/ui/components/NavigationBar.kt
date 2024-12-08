package com.example.brewco.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.brewco.R
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Beige


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, onMenuClick: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(36.dp) // Cambia el tamaño del icono
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Acción del icono de usuario */ }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "User Icon",
                    modifier = Modifier.size(36.dp) // Cambia el tamaño del icono
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithText(
    title: String,
    text1: String,
    text2: String,
    onActionClick: () -> Unit,
    navController: NavController, // Agrega el NavController como parámetro
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        },
        navigationIcon = {
            Text(
                text = text1,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        navController.popBackStack() // Esto navegará hacia la pantalla anterior
                    },
                fontSize = 16.sp,
                color = Brown
            )
        },
        actions = {
            Text(
                text = text2,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onActionClick() },
                fontSize = 16.sp,
                color = Brown
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            actionIconContentColor = Color.Black
        )
    )
}


@Composable
fun CustomBottomNavBar(navController: NavController) {
    // Obtener la ruta actual para determinar el icono activo
    val pantallaActual = navController.currentBackStackEntryAsState().value?.destination?.route

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Brown, // Fondo personalizado
    ) {
        Spacer(modifier = Modifier.weight(1f))

        // Botón de calendario
        IconButton(
            onClick = { navController.navigate("agendaScreen") }, modifier = Modifier
                .size(60.dp)
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Agenda",
                modifier = Modifier.size(40.dp), // Tamaño del icono
                tint = if (pantallaActual == "agendaScreen?added={added}&delete={delete}&edited={edited}") DarkBrown else Color.White // Cambia el color si es la pantalla activa
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de Clientes
        IconButton(
            onClick = { navController.navigate("customerScreen?added={added}&deleted={deleted}&edited={edited}") },
            modifier = Modifier
                .size(60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fa6solidusergroup), // Recurso de drawable
                contentDescription = "Clientes",
                modifier = Modifier.size(40.dp), // Tamaño del icono
                colorFilter = ColorFilter.tint(
                    if (pantallaActual == "customerScreen?added={added}&deleted={deleted}&edited={edited}") DarkBrown else Color.White
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de Inicio
        IconButton(
            onClick = { navController.navigate("homeScreen") }, modifier = Modifier
                .size(60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.house), // Recurso de drawable
                contentDescription = "Home",
                modifier = Modifier.size(33.dp), // Tamaño del icono
                colorFilter = ColorFilter.tint(if (pantallaActual == "homeScreen") DarkBrown else Color.White)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de Inventario
        IconButton(
            onClick = { navController.navigate("inventoryScreen?added={added}&delete={delete}&edited={edited}") },
            modifier = Modifier
                .size(60.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bxsbox), // Recurso de drawable
                contentDescription = "Inventario",
                modifier = Modifier.size(40.dp), // Tamaño del icono
                colorFilter = ColorFilter.tint(if (pantallaActual == "inventoryScreen?added={added}&delete={delete}&edited={edited}") DarkBrown else Color.White)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de notificaciones
        IconButton(
            onClick = { navController.navigate("notificationScreen") }, modifier = Modifier
                .size(60.dp)
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notificaciones",
                modifier = Modifier.size(45.dp), // Tamaño del icono
                tint = if (pantallaActual == "notificationScreen") DarkBrown else Color.White
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CustomFloatingActionButton(navController: NavController,onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Brown
    ) {
        Icon(
            Icons.Default.Add, contentDescription = "Agregar",
            modifier = Modifier.size(36.dp),
            Color.White
        )
    }
}


