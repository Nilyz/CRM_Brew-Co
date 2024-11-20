package com.example.brewco.ui.screens.inventory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.brewco.R
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.*
import com.example.brewco.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun InventoryScreen(navHostController: NavHostController, viewModel: StockViewModel = viewModel()) {
    // Observa la lista de productos
    val productList by viewModel.products.collectAsState()

    Scaffold(
        topBar = { TopBar(title = "Inventario") },
        bottomBar = { CustomBottomNavBar(navHostController) },
        floatingActionButton = {
            PlusButton(navHostController, onClick = { navHostController.navigate("loginScreen")})
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(productList) { product ->
                        ProductItem(product = product, navHostController= navHostController)
                    }
                }
            }
        }
    )
}

@Composable
fun ProductItem(product: Product,navHostController: NavHostController) {
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
                    .fillMaxHeight()
                    .border(width = 2.dp, color = Brown),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cafe),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.80f)
                        .padding(bottom = 8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(width = 2.dp, color = Beige),
                    contentScale = ContentScale.Crop
                )
                Text("${product.nombre}",color= DarkBrown,fontWeight = FontWeight.Bold,fontSize = 18.sp)
            }


            Column(
                modifier = Modifier
                    .weight(0.60f)
                    .fillMaxHeight()
                    .border(width = 2.dp, color = Brown)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .border(width = 2.dp, color = Brown)
                    .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Text("Categoría: ", color= DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.categoria}",color= DarkBrown)
                    }
                    Row() {
                        Text("Stock disponible: ",color= DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.inventario}",color= DarkBrown)
                    }
                    Row() {
                        Text("Stock mínimo: ",color= DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.inventario_minimo}",color= DarkBrown)
                    }
                    Row() {
                        Text("Precio: ",color= DarkBrown,fontWeight = FontWeight.Bold)
                        Text("${product.precio} €",color= DarkBrown)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    EditButton(navHostController, onClick = { navHostController.navigate("loginScreen")})
                }
            }
        }
    }
}
