package com.example.brewco.ui.screens.customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Client
import com.example.brewco.ui.theme.*
import com.example.brewco.ui.components.TopBarWithText


@Composable
fun CustomerDetailsScreen(
    navHostController: NavHostController,
    clientId: String,
    viewModel: CustomerViewModel = viewModel(),
) {
    var client by remember { mutableStateOf<Client?>(null) }

    // Cargar el cliente desde Firebase
    LaunchedEffect(clientId) {
        viewModel.getClienteById(clientId) { fetchedClient ->
            client = fetchedClient
        }
    }

    Scaffold(
        topBar = {
            TopBarWithText(
                title = "Info.",
                text1 = "Atrás",
                text2 = "Editar",
                navController = navHostController,
                onActionClick = { navHostController.navigate("editCustomerScreen/${clientId}") }
            )
        },
        containerColor = Color.White,
    ) { paddingValues ->
        client?.let { cliente ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                // Contenido principal con peso para ocupar el espacio restante
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(1f), // Ocupa el espacio restante
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Nombre del cliente
                    Text(
                        text = "${cliente.nombre} ${cliente.apellido}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown,
                        lineHeight = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )


                    // Teléfono
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.phone_svgrepo_com),
                            contentDescription = "Phone",
                            modifier = Modifier
                                .size(35.dp)
                        )
                        Text(
                            text = cliente.telefono,
                            fontWeight = FontWeight.Normal,
                            color = DarkBrown,
                            fontSize = 18.sp,
                        )
                    }
                    // Última compra
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Agenda",
                            modifier = Modifier
                                .size(35.dp),
                            tint = DarkBrown
                        )
                        Text(
                            text = "Última compra: ${cliente.ultCompra}",
                            fontWeight = FontWeight.Normal,
                            color = DarkBrown,
                            fontSize = 18.sp,
                        )
                    }
                    // Correo electrónico
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mail_svgrepo_com),
                            contentDescription = "Mail",
                            modifier = Modifier
                                .size(35.dp)

                        )

                        Text(
                            text = "Correo: ${cliente.correo}",
                            fontWeight = FontWeight.Normal,
                            color = DarkBrown, fontSize = 18.sp,
                        )
                    }
                    // Puntos
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.present_svgrepo_com),
                            contentDescription = "Present",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            text = "Puntos: ${cliente.puntos}",
                            color = DarkBrown, fontSize = 18.sp,
                        )
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Agregar",
                            modifier = Modifier.size(30.dp),
                            tint = Yellow
                        )
                    }
                }

                // Sección de notas fija al final
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                        .background(Beige)

                ) {
                    Column(
                        modifier = Modifier
                            .padding(32.dp)
                    ) {
                        Text(
                            text = "Notas:",
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = cliente.notas,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                }
            }
        }
    }
}

