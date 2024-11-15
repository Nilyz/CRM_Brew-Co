package com.example.brewco

import android.os.Bundle
import com.example.brewco.ui.screens.*
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brewco.ui.theme.BrewCoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrewCoTheme {
                // Crear el NavController para la navegación
                val navHostController = rememberNavController()

                // Usar Scaffold con un NavHost para manejar las pantallas
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Configurar el NavHost con las pantallas
                    NavHost(
                        navController = navHostController,
                        startDestination = "splashScreen"
                    ) {
                        composable("splashScreen") {
                            StartSplashScreen(navHostController = navHostController, modifier = Modifier.padding(innerPadding))
                        }
                        composable("loginScreen") {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BrewCoTheme {
        Greeting("Android")
    }
}