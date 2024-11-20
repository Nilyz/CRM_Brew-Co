package com.example.brewco.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.brewco.ui.screens.agenda.AgendaScreen
import com.example.brewco.ui.screens.customer.CustomerScreen
import com.example.brewco.ui.screens.home.HomeScreen
import com.example.brewco.ui.screens.inventory.InventoryScreen
import com.example.brewco.ui.screens.login.AuthViewModel
import com.example.brewco.ui.screens.login.LoginScreen
import com.example.brewco.ui.screens.notification.NotificationtScreen
import com.example.brewco.ui.screens.splashScreen.StartSplashScreen


@Composable
fun NavigationWrapper (navHostController: NavHostController, authViewModel: AuthViewModel) {

    NavHost(navController = navHostController, startDestination = "homeScreen") {

        composable ("startSplashScreen") { StartSplashScreen(navHostController) }
        composable ("loginScreen") { LoginScreen(navHostController, authViewModel) }
        composable ("inventoryScreen") { InventoryScreen(navHostController) }
        composable ("homeScreen") { HomeScreen(navHostController) }
        composable ("customerScreen") { CustomerScreen(navHostController) }
        composable ("notificationScreen") { NotificationtScreen(navHostController) }
        composable ("agendaScreen") { AgendaScreen(navHostController) }

    }
}










