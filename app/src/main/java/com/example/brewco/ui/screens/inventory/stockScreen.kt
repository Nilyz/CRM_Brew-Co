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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.brewco.R
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.*
import com.example.brewco.ui.theme.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch





@Composable
fun InventoryScreen(navHostController: NavHostController, viewModel: StockViewModel = viewModel()) {
    // Observa la lista de productos
    val productList by viewModel.products.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val addedMessage = navHostController.currentBackStackEntry?.arguments?.getString("added")
    val deleteMessage = navHostController.currentBackStackEntry?.arguments?.getString("delete")
    val editedMessage = navHostController.currentBackStackEntry?.arguments?.getString("edited")


    LaunchedEffect(addedMessage, deleteMessage, editedMessage) {
        when {
            addedMessage == "true" -> {
                scope.launch {
                    snackbarHostState.showSnackbar("Producto añadido con éxito!")
                }
            }
            deleteMessage == "true" -> {
                scope.launch {
                    snackbarHostState.showSnackbar("Producto eliminado con éxito!")
                }
            }
            editedMessage == "true" -> {
                scope.launch {
                    snackbarHostState.showSnackbar("Producto editado con éxito!")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadProducts()  //recargar productos cada vez que se entra a la pantalla
    }
    ModalNavigationDrawer(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        drawerContent = {
            CustomDrawer(
                navHostController = navHostController,
                onLogoutClick = {
                    navHostController.navigate("splashScreen") {
                        popUpTo(0) // Limpia la pila de navegación
                    }
                }
            )
        }
    ) { }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState,modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp))
        },
        topBar = {
            TopBar(title = "Inventario", onMenuClick = {
                scope.launch {

                    if (drawerState.isClosed) {
                        drawerState.open()
                    } else {
                        drawerState.close()
                    }


                }
            })
        },
        bottomBar = { CustomBottomNavBar(navHostController) },
        floatingActionButton = {
            PlusButton(
                navHostController,
                onClick = { navHostController.navigate("addProductScreen") })
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
                        ProductItem(product = product, navHostController = navHostController)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    )
}

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
                        .clip(RoundedCornerShape(16.dp))
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
                        .fillMaxHeight(0.75f)
                        //.border(width = 2.dp, color = Brown)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row() {
                        Text("Categoría: ", color = DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.categoria}", color = DarkBrown)
                    }
                    Row() {
                        Text("Stock disponible: ", color = DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.inventario}", color = DarkBrown)
                    }
                    Row() {
                        Text("Stock mínimo: ", color = DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.inventario_minimo}", color = DarkBrown)
                    }
                    Row() {
                        Text("Precio: ", color = DarkBrown, fontWeight = FontWeight.Bold)
                        Text("${product.precio} €", color = DarkBrown)
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
