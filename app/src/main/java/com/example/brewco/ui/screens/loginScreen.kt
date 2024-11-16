package com.example.brewco.ui.screens

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.R
import com.example.brewco.ui.theme.*
import com.example.brewco.ui.components.*
import com.example.brewco.ui.viewmodel.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController ,authViewModel: AuthViewModel ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthViewModel.AuthState.Authenticated -> navHostController.navigate("homeScreen")
            is AuthViewModel.AuthState.Error -> Toast.makeText(
                context, (authState as AuthViewModel.AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brown)
            .padding(top = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.40f)
        ) {

            ArrowButton {
                navHostController.navigate("startSplashScreen")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(
                    "Iniciar Sesión",
                    style = TextStyle(
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color= Cream

                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Inicia sesión con el correo de la empresa para poder acceder",
                    style = TextStyle(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color= Cream
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Cream, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .padding(30.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column() {
                TextField(
                    value = email,
                    labelText = "Email",
                    onValueChange = { email = it }
                )
                Spacer(modifier = Modifier.height(18.dp))
                TextField(
                    value = password,
                    labelText = "Contraseña" ,
                    onValueChange = { password = it }
                )
            }
            TextButton(onClick = {
            }) {
                Text(
                    "Olvidaste la contraseña?",
                    style = TextStyle(
                        fontSize = 15.sp,
                        textAlign = TextAlign.Right,
                                color= DarkBrown
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            CustomButton(
                text = "Iniciar Sesión",
                onClick = {
                    authViewModel.login(email, password)
                },
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recuérdame",
                    style = TextStyle(
                        color= DarkBrown
                    ),)
                ToggleSwitch()
            }

        }

    }

}
