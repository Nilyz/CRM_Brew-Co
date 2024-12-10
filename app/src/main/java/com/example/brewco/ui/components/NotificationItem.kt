package com.example.brewco.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown

@Composable
fun NotificationItem(alert: Alert, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navegar a la pantalla de detalles y pasar el objeto 'alert' como argumento
                navHostController.navigate("agendaViewEvent/${alert.id}")
            },
        colors = CardDefaults.cardColors(
            containerColor = Beige
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${alert.titulo}",
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(end = 8.dp),
                    //.border(width = 2.dp, color = Color.Black),
                    color = DarkBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )

                Text(
                    text = "${alert.fechaFin}",
                    modifier = Modifier
                        .weight(0.3f),
                    //.border(width = 2.dp, color = Color.Black),
                    textAlign = TextAlign.End, color = Brown, fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row() {
                Text(
                    text = "${alert.horaInicio}:${alert.minutosInicio} - ${alert.horaFin}:${alert.minutosFin}",
                    fontSize = 14.sp, // Consistencia en el tamaño del texto
                    color = Brown
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    "${alert.descripcion}",
                    color = DarkBrown,
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(bottom = 8.dp),
                    fontSize = 16.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify
                )
            }

        }
    }
}