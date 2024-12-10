package com.example.brewco.ui.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.example.brewco.ui.theme.Beige
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.DarkBrown
import com.example.brewco.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(color = Color.Black, fontSize = 16.sp)
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = labelText) },
        singleLine = true,
        modifier = modifier,
        textStyle = textStyle,
        visualTransformation = PasswordVisualTransformation(),  // Aquí se aplica la transformación visual
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Beige,
            focusedIndicatorColor = Brown,
            unfocusedIndicatorColor = Beige,
            focusedLabelColor = DarkBrown,
            unfocusedLabelColor = Gray
        )
    )
}
