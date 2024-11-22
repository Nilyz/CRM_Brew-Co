package com.example.brewco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.brewco.ui.screens.agenda.AgendaScreen
import com.example.brewco.ui.screens.agenda.AgendaAddEvent
import com.example.brewco.ui.screens.customer.CustomerScreen
import com.example.brewco.ui.screens.home.HomeScreen
import com.example.brewco.ui.screens.inventory.*
import com.example.brewco.ui.screens.login.AuthViewModel
import com.example.brewco.ui.screens.login.LoginScreen
import com.example.brewco.ui.screens.notification.NotificationtScreen
import com.example.brewco.ui.screens.splashScreen.StartSplashScreen


@Composable
fun NavigationWrapper(navHostController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController = navHostController, startDestination = "homeScreen") {

        composable("startSplashScreen") { StartSplashScreen(navHostController) }
        composable("loginScreen") { LoginScreen(navHostController, authViewModel) }

        /*---------------------------PANTALLAS DE INVENTARIO-----------------*/
        composable(
            route = "inventoryScreen?added={added}&delete={delete}&edited={edited}",
            arguments = listOf(
                navArgument("added") { defaultValue = "false" },
                navArgument("delete") { defaultValue = "false" },
                navArgument("edited") { defaultValue = "false" }
            )
        ) {
            InventoryScreen(navHostController)
        }
        composable("addProductscreen") { AddProductScreen(navHostController) }
        composable(
            "editProductScreen/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            productId?.let {
                EditProductScreen(navHostController, productId = it)
            }
        }
        /*----------------------------PANTALLAS DE HOME---------------------*/
        composable("homeScreen") { HomeScreen(navHostController) }

        /*----------------------------PANTALLAS DE CLIENTE----------------------*/
        composable("customerScreen") { CustomerScreen(navHostController) }

        /*----------------------------PANTALLAS DE NOTIFICACIONES--------------------*/
        composable("notificationScreen") { NotificationtScreen(navHostController) }

        /*----------------------------PANTALLAS DE AGENDA-----------------------------*/
        composable("agendaScreen") { AgendaScreen(navHostController) }
        composable("agendaAddEvent/{selectedDate}") { backStackEntry ->
            val selectedDate = backStackEntry.arguments?.getString("selectedDate")
            if (selectedDate != null) {
                AgendaAddEvent(navHostController, selectedDate)
            }
        }


    }
}


