package com.example.brewco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.brewco.ui.navigation.NavigationWrapper
import com.example.brewco.ui.theme.BrewCoTheme
import com.example.brewco.ui.screens.login.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrewCoTheme {
                val navController = rememberNavController()
                val authViewModel = AuthViewModel()
                NavigationWrapper(navHostController = navController, authViewModel = authViewModel)
            }
        }
    }
}

