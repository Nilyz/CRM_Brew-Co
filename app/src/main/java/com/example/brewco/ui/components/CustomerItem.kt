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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Client
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Yellow

@Composable
fun CustomerItem(client: Client, navHostController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navHostController.navigate("customerDetailsScreen/${client.id}")
            },
        colors = CardDefaults.cardColors(containerColor = Beige)

    ) {

        Column(
            modifier = Modifier
                .border(
                    width = 2.dp, color = Color.Transparent
                )
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = "${client.nombre} ${client.apellido}",
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = DarkBrown,
                modifier = Modifier
                    .fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Puntos: ",
                    color = DarkBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
                Text(

                    text = "${client.puntos}",
                    color = DarkBrown,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
                Icon(
                    Icons.Default.Star, contentDescription = "Agregar",
                    modifier = Modifier.size(20.dp),
                    Yellow
                )
            }
            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp, Color.Transparent
                    )
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .border(
                            width = 2.dp, Color.Transparent
                        )
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "Tel: ",
                            color = DarkBrown,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier
                        )
                        Text(
                            text = "${client.telefono}",
                            color = DarkBrown,
                            fontSize = 16.sp,
                            modifier = Modifier
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp, Color.Transparent
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Últ Compra: ",
                    color = DarkBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
                Text(
                    text = "${client.ultCompra}",
                    color = DarkBrown,
                    fontSize = 16.sp,
                    modifier = Modifier
                )
            }

        }
    }

}