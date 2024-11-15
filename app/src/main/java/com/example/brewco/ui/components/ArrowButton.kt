package com.example.brewco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.brewco.R

@Composable
fun ArrowButton(
    onClick: () -> Unit,
) {
    Button(

        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.size(70.dp),
        contentPadding = PaddingValues(0.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.arrow_sm_right_svgrepo_com),
            contentDescription = "Flecha",
            modifier = Modifier
                .size(70.dp)
                .graphicsLayer(
                    rotationZ = 180f
                )
        )

    }
}