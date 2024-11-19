package com.example.brewco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Gray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        textStyle = androidx.compose.ui.text.TextStyle(color = DarkBrown), // Estilo del texto
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray, // Fondo del campo
            focusedIndicatorColor = Brown, // Línea inferior enfocada
            unfocusedIndicatorColor = Gray, // Línea inferior no enfocada
            focusedLabelColor = Brown, // Color de la etiqueta enfocada
            unfocusedLabelColor = Gray // Color de la etiqueta no enfocada
        )
    )
}