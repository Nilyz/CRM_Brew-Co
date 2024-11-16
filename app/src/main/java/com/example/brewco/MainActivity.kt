package com.example.brewco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.brewco.navigation.NavigationWrapper
import com.example.brewco.ui.theme.BrewCoTheme
import com.example.brewco.ui.viewmodel.AuthViewModel

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

