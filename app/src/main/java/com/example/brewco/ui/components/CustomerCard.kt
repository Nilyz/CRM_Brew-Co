package com.example.brewco.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewco.ui.theme.Beige


@Composable
fun CustomerCard(
    name: String,
    lastName: String,
    points: Int,
    phone: String,
    lastPurchase: String
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(180.dp)
            .height(180.dp),
        colors = CardDefaults.cardColors(containerColor = Beige), // Color beige
        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Nombre y Apellido (concatenado previamente para centrarlo correctamente)
            Row(
                modifier = Modifier.fillMaxWidth(), // Ocupa todo el ancho disponible
                horizontalArrangement = Arrangement.Center // Centra el contenido horizontalmente
            ) {
                Text(
                    text = "$name", // Juntar nombre y apellido
                    style = typography.bodyLarge.copy(
                        fontSize = 20.sp, // Tamaño del texto
                        fontWeight = FontWeight.Bold // Negrita
                    ),
                    color = Color.Black
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Puntos y Estrella
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Puntos: $points",
                    style = typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD700), // Color dorado para la estrella
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Teléfono
            Row {
                Text(
                    text = "Tel: $phone",
                    style = typography.bodySmall,
                    color = Color.Black,
                    fontSize = 12.sp,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Última Compra
            Row {
                Text(
                    text = "Última compra: $lastPurchase",
                    style = typography.bodySmall,
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }
    }
}
