package com.example.brewco.ui.screens.notification

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.data.model.Alert
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomDrawer
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun NotificationtScreen(navHostController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawer(
                navHostController = navHostController,
                onLogoutClick = {
                    navHostController.navigate("splashScreen") {
                        popUpTo(0) // Limpia la pila de navegación
                    }
                })
        },
    ) { }
    Scaffold(
        topBar = {
            TopBar(title = "Notificaciones", onMenuClick = {
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

                    items(10) { index ->
                        NotificationItem(index)
                    }

                }
            }
        }
    )


}

@Composable
fun NotificationItem(index: Int) {
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
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Título",
                    modifier = Modifier
                        .weight(0.7f)
                        .padding(end = 8.dp)
                        .border(width = 2.dp, color = Color.Black),
                    color = DarkBrown,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Text(
                    text = "12/6/2024",
                    modifier = Modifier
                        .weight(0.3f)
                        .border(width = 2.dp, color = Color.Black),
                    textAlign = TextAlign.End, color = DarkBrown
                )
            }
            Row() {
                Text("15:30", color = DarkBrown)
                Text("-", color = DarkBrown)
                Text("16:00", color = DarkBrown)
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "reserva de 5 personas en la terraza aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    color = DarkBrown,
                    modifier = Modifier
                        .weight(0.3f)
                        .border(width = 2.dp, color = Color.Black),

                    )
            }
        }
    }
}