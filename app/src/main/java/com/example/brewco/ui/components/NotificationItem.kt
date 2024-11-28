package com.example.brewco.ui.components

import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown

@Composable
fun NotificationItem(alert: Alert) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
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
                    textAlign = TextAlign.End, color = Brown
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row() {
                Text("${alert.horaInicio}:${alert.minutosInicio}", color = Brown, fontSize = 14.sp)
                Text("-", color = Brown, fontSize = 14.sp)
                Text("${alert.horaFin}:${alert.minutosFin}", color = Brown, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "${alert.descripcion}",
                    color = DarkBrown,
                    modifier = Modifier
                        .weight(0.3f)
                        //.border(width = 2.dp, color = Color.Black)
                        .padding(bottom = 8.dp),

                    )
            }
        }
    }
}