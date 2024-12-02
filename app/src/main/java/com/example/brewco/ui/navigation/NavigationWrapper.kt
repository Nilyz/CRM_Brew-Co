package com.example.brewco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.brewco.data.model.Alert
import com.example.brewco.ui.screens.agenda.AgendaScreen
import com.example.brewco.ui.screens.agenda.AgendaAddEvent
import com.example.brewco.ui.screens.agenda.AgendaViewEvent
import com.example.brewco.ui.screens.agenda.AgendaEditEvent
import com.example.brewco.ui.screens.customer.CustomerScreen
import com.example.brewco.ui.screens.home.HomeScreen
import com.example.brewco.ui.screens.inventory.*
import com.example.brewco.ui.screens.login.AuthViewModel
import com.example.brewco.ui.screens.login.LoginScreen
import com.example.brewco.ui.screens.notification.NotificationScreen
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
        composable("notificationScreen") { NotificationScreen(navHostController) }

        /*----------------------------PANTALLAS DE AGENDA-----------------------------*/
        composable(
            route = "agendaScreen?added={added}&delete={delete}&edited={edited}",
            arguments = listOf(
                navArgument("added") { defaultValue = "false" },
                navArgument("delete") { defaultValue = "false" },
                navArgument("edited") { defaultValue = "false" }
            )
        ) {
            AgendaScreen(navHostController)
        }
        composable("agendaAddEvent/{selectedDate}") { backStackEntry ->
            val selectedDate = backStackEntry.arguments?.getString("selectedDate")
            if (selectedDate != null) {
                AgendaAddEvent(
                    navHostController = navHostController,
                    selectedDate = selectedDate,
                    isAllDay = false // O cualquier valor booleano que desees
                )

            }
        }

        composable(
            "agendaViewEvent/{alertId}",
            arguments = listOf(navArgument("alertId") { type = NavType.StringType })
        ) { backStackEntry ->
            val alertId = backStackEntry.arguments?.getString("alertId")
            alertId?.let {
                AgendaViewEvent(navHostController, alertId = it)
            }
        }

        composable(
            "agendaEditEvent/{alertId}",
            arguments = listOf(navArgument("alertId") { type = NavType.StringType })
        ) { backStackEntry ->
            val alertId = backStackEntry.arguments?.getString("alertId")
            alertId?.let {
                AgendaEditEvent(navHostController, alertId = it, isAllDay = false)
            }
        }


    }
}


