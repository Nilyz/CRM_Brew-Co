package com.example.brewco.ui.screens.inventory

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.ui.components.*
import com.example.brewco.ui.theme.DarkBrown


@Composable
fun AddProductScreen(navHostController: NavHostController) {
    var stockDisponible by remember { mutableStateOf(20) }
    var stockMinimo by remember { mutableStateOf(20) }

    Scaffold(
        topBar = { TopBarWithText(title = "Nuevo Producto",text1="Cancelar",text2="Añadir") },

        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier=Modifier.fillMaxWidth()
                        .padding(16.dp)
                ){
                    ImagePicker()
                    TextField(
                        value = "",
                        labelText = "Producto" ,
                        onValueChange = {  }
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    TextField(
                        value = "",
                        labelText = "Categoría" ,
                        onValueChange = {  }
                    )
                    StockInputField("Stock disponible",value=stockDisponible,onValueChange = { newValue -> stockDisponible = newValue })
                    StockInputField("Stock mínimo",value=stockMinimo,onValueChange = { newValue -> stockMinimo = newValue })

                }
            }
        }
    )
}

