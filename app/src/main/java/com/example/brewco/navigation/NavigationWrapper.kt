package com.example.brewco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.brewco.ui.screens.*
import com.example.brewco.ui.viewmodel.AuthViewModel


@Composable
fun NavigationWrapper (navHostController: NavHostController, authViewModel: AuthViewModel) {

    NavHost(navController = navHostController, startDestination = "HomeScreen") {

        composable ("startSplashScreen") { StartSplashScreen(navHostController) }
        composable ("loginScreen") {LoginScreen(navHostController, authViewModel)}
        composable ("inventoryScreen") {InventoryScreen(navHostController)}
        composable ("homeScreen") { HomeScreen(navHostController)}
        composable ("customerScreen") {CustomerScreen(navHostController)}
        composable ("notificationScreen") {NotificationtScreen(navHostController)}
        composable ("agendaScreen") {AgendaScreen(navHostController)}

    }
}










