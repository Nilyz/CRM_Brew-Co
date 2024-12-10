package com.example.brewco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown


@Composable
fun EventCard(alert: Alert, navHostController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp) // Altura fija para pruebas; ajusta según sea necesario
            .background(
                color = Beige, // Color del fondo de la tarjeta
                shape = RoundedCornerShape(16.dp) // Esquinas redondeadas
            )
            .padding(15.dp) // Padding interno de la tarjeta
            .clickable {
                // Navegar a la pantalla de detalles y pasar el objeto 'alert' como argumento
                navHostController.navigate("agendaViewEvent/${alert.id}")
            }
    ) {
        // Barra lateral de color
        Box(
            modifier = Modifier
                .width(8.dp) // Ancho fijo para la barra
                .fillMaxHeight() // Altura fija para pruebas; ajusta según sea necesario
                .clip(RoundedCornerShape(4.dp)) // Redondea las esquinas con un radio de 4.dp (ajústalo según sea necesario)
                .background(Brown) // Color verde de la barra lateral
        )

        Spacer(modifier = Modifier.width(20.dp))

        // Contenido de la tarjeta
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Título
            Text(
                text = "${alert.titulo}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBrown,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Subtítulo (Nombre)
            Text(
                text = "${alert.empleado}",
                fontSize = 14.sp,
                color = Brown
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Hora e icono
            Row(
                verticalAlignment = Alignment.CenterVertically // Asegura alineación vertical
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "Reloj",
                    modifier = Modifier.size(25.dp), // Tamaño del icono
                    tint = DarkBrown,
                )
                Spacer(modifier = Modifier.width(8.dp)) // Espaciado entre el icono y el texto
                Text(
                    text = "${alert.horaInicio}:${alert.minutosInicio} - ${alert.horaFin}:${alert.minutosFin}",
                    fontSize = 16.sp, // Consistencia en el tamaño del texto
                    color = DarkBrown
                )
            }

        }
    }
}
