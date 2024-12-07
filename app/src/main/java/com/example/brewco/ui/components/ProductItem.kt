package com.example.brewco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Product
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown

@Composable
fun ProductItem(product: Product, navHostController: NavHostController) {
    val categoryImageRes = when (product.categoria) {
        "Leche" -> R.drawable.leche
        "Cafe" -> R.drawable.cafe
        "Te" -> R.drawable.te
        "Horneados" -> R.drawable.bolleria
        else -> R.drawable.logobrewco
    }

    val painter = painterResource(id = categoryImageRes)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = Beige
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .weight(0.40f)
                    //.border(width = 2.dp, color = Brown)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Image(
                    painter = painter,
                    contentDescription = "Producto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.80f)
                        .padding(bottom = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        //.border(width = 2.dp, color = Beige)
                        .background(Brown),
                    contentScale = ContentScale.Crop
                )
                Text(
                    "${product.nombre}",
                    color = DarkBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .weight(0.60f)
                    .fillMaxHeight()
                //.border(width = 2.dp, color = Brown)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.80f)
                        //.border(width = 2.dp, color = Brown)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Text("Categoría: ", color = DarkBrown, fontWeight = FontWeight.Bold,fontSize = 16.sp)
                        Text("${product.categoria}", color = DarkBrown,fontSize = 16.sp)
                    }
                    Row() {
                        Text("Stock disponible: ", color = DarkBrown, fontWeight = FontWeight.Bold,fontSize = 16.sp)
                        Text("${product.inventario}", color = DarkBrown,fontSize = 16.sp)
                    }
                    Row() {
                        Text("Stock mínimo: ", color = DarkBrown, fontWeight = FontWeight.Bold,fontSize = 16.sp)
                        Text("${product.inventario_minimo}", color = DarkBrown,fontSize = 16.sp)
                    }
                    Row() {
                        Text("Precio: ", color = DarkBrown, fontWeight = FontWeight.Bold,fontSize = 16.sp)
                        Text("${product.precio} €", color = DarkBrown,fontSize = 16.sp)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    EditButton(
                        navHostController,
                        onClick = { navHostController.navigate("editProductscreen/${product.id}") })
                }
            }
        }
    }
}
