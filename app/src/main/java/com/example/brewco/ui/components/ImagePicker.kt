package com.example.brewco.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.brewco.R
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown

@Composable
fun ImagePicker() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Registrar el contrato para seleccionar una imagen
    val pickImage = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // El URI de la imagen seleccionada
        imageUri = uri
    }

    // UI para mostrar la imagen seleccionada
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.35f)
            .clip(RoundedCornerShape(16.dp))
            .background(Beige)
    )  {
        imageUri?.let {
            // Mostrar la imagen seleccionada
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Seleccionada",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            // Si no hay imagen seleccionada, que se quede en blanco

        }


        // Botón para abrir la galería de imágenes
        IconButton(
            onClick = { pickImage.launch("image/*") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(5.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.image_plus_svgrepo_com),
                contentDescription = "Home",
                modifier = Modifier.size(34.dp),

                )
        }
    }
}