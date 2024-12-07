package com.example.brewco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.screens.notification.NotificationViewModel
import com.example.brewco.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun HomeNotificationBox(
    navHostController: NavHostController,
    viewModel: NotificationViewModel = viewModel()
) {

    val alerts by viewModel.alerts.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.getAlertByPrompt()
    }

    // Selecciona hasta 3 alertas aleatorias
    val randomAlerts = if (alerts.size > 3) alerts.shuffled().take(3) else alerts

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Beige),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(Brown)
                .clickable {
                    navHostController.navigate("notificationScreen")
                },
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Default.Notifications,
                contentDescription = "Notificaciones",
                tint = Cream,
                modifier = Modifier.size(35.dp)
            )
            Text(
                text = "Notificaciones pendientes",
                color = Cream,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            IconButton(onClick = {
                navHostController.navigate("notificationScreen")
            }) {
                Image(
                    painter = painterResource(id = R.drawable.triangle_right_svgrepo_com),
                    contentDescription = "Arrow",
                    modifier = Modifier.size(30.dp),

                    )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            // Muestra las alertas seleccionadas
            if (randomAlerts.isNotEmpty()) {
                randomAlerts.forEach { alert ->
                    Text(
                        text = "* ${alert.titulo}",
                        color = DarkBrown,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            } else {
                Text(
                    text = "No hay notificaciones disponibles.",
                    color = DarkBrown,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}
