package com.example.brewco.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.brewco.ui.theme.DarkBrown

@Composable
fun StockInputField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    // Row principal que contiene el label y el input con botones
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(label)

        // Botón para decrementar el valor
        IconButton(onClick = { if (value > 0) onValueChange(value - 1) }) {
            Icon(
                Icons.Default.Close, contentDescription = "Decrementar",
                modifier = Modifier.size(30.dp),
                tint = DarkBrown
            )
        }
        @Composable
        fun NumberInput(
            label: String,
            value: Int,
            onValueChange: (Int) -> Unit
        ) {
            // Row principal que contiene el label y el input con botones
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(label)

                // Botón para decrementar el valor
                IconButton(onClick = { if (value > 0) onValueChange(value - 1) }) {
                    Icon(
                        Icons.Default.Close, contentDescription = "Decrementar",
                        modifier = Modifier.size(30.dp),
                        tint = DarkBrown
                    )
                }

                // Campo de texto que muestra el valor actual
                Text(
                    text = "$value",
                    modifier = Modifier.width(50.dp),
                    textAlign = TextAlign.Center
                )

                // Botón para incrementar el valor
                IconButton(onClick = { onValueChange(value + 1) }) {
                    Icon(
                        Icons.Default.Add, contentDescription = "Incrementar",
                        modifier = Modifier.size(30.dp),
                        tint = DarkBrown

                    )
                }
            }
        }

        // Campo de texto que muestra el valor actual
        Text(
            text = "$value",
            modifier = Modifier.width(50.dp),
            textAlign = TextAlign.Center
        )

        // Botón para incrementar el valor
        IconButton(onClick = { onValueChange(value + 1) }) {
            Icon(
                Icons.Default.Add, contentDescription = "Incrementar",
                modifier = Modifier.size(30.dp),
                tint = DarkBrown

            )
        }
    }
}
