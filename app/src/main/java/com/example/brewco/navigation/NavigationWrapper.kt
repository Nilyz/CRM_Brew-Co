package com.example.brewco.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.brewco.ui.screens.*


@Composable
fun NavigationWrapper (navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "startSplashScreen") {

        composable ("startSplashScreen") { StartSplashScreen(navHostController) }
        composable ("loginScreen") {LoginScreen(navHostController)}
        composable ("inventoryScreen") {InventoryScreen(navHostController)}
        composable ("homeScreen") { HomeScreen(navHostController)}
        composable ("customerScreen") {CustomerScreen(navHostController)}
        composable ("notificationScreen") {NotificationtScreen(navHostController)}
        composable ("agendaScreen") {AgendaScreen(navHostController)}

    }
}










