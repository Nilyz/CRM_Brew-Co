package com.example.brewco.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.brewco.ui.theme.*
import com.example.brewco.ui.components.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController ,authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()
    val context = LocalContext.current
    var isChecked by remember { mutableStateOf(false) } // Desactivado por defecto




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
                CustomTextField(
                    value = email,
                    labelText = "Email",
                    onValueChange = { email = it },
                    modifier=Modifier.fillMaxWidth(

                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    value = password,
                    labelText = "Contraseña" ,
                    onValueChange = { password = it },
                    modifier=Modifier.fillMaxWidth(

                    )
                )
            }
            TextButton(onClick = {
            }) {
                Text(
                    "Olvidaste la contraseña?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right,
                                color= DarkBrown
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                text = "Iniciar Sesión",contColor= Brown,textColor=Cream,fontSize = 18.sp,contentPadding = PaddingValues(16.dp),
                onClick = {
                    authViewModel.login(email, password)
                },
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recuérdame",
                    fontSize = 16.sp,
                    style = TextStyle(
                        color= DarkBrown
                    ),)
                ToggleSwitch(
                    checked = isChecked,
                    onCheckedChange = { newState ->
                        isChecked = newState
                    }
                )
            }

        }

    }

}
