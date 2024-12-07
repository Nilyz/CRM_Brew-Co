package com.example.brewco.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Gray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier // Agrega este parámetro para aceptar un modificador externo
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        singleLine = true,
        modifier = modifier, // Usa el modificador proporcionado
        textStyle = androidx.compose.ui.text.TextStyle(color = DarkBrown,  fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Beige,
            focusedIndicatorColor = Brown,
            unfocusedIndicatorColor = Beige,
            focusedLabelColor = DarkBrown,
            unfocusedLabelColor = Gray
        )
    )
}
