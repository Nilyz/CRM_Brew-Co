package com.example.brewco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import com.example.brewco.ui.theme.*

@Composable
fun CustomButton(
    text: String,
    textColor: Color,
    contColor: Color,
    fontSize: TextUnit,
    contentPadding: PaddingValues,
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = contentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = contColor
        ),
        shape = RoundedCornerShape(8.dp)

    ) {
        Text(
            text = text,
            color = textColor,
            style = TextStyle(
                fontSize = fontSize
            )
        )
    }
}