package com.example.brewco.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.brewco.ui.theme.Gray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value:String,
    labelText: String,
){
    OutlinedTextField(
        value = value,
        onValueChange = { },
        label = { Text(text = labelText) },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Gray,
            unfocusedBorderColor = Gray
        )
    )
}