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
    labelText: String
) {
    TextField(
        value = value,
        onValueChange = { /* Acción de cambio de texto */ },
        label = { Text(text = labelText) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Gray,
            focusedIndicatorColor = Brown,
            unfocusedIndicatorColor = Gray,
            focusedTextColor = DarkBrown,
            unfocusedTextColor = Gray
        )
    )
}