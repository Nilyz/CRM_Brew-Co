package com.example.brewco.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewco.R
import com.example.brewco.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockInputField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Row principal que contiene el label y el input con botones
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(label,fontWeight = FontWeight.Bold)

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            // Botón para decrementar el valor
            IconButton(onClick = { if (value > 0) onValueChange(value - 1) }) {
                Image(
                    painter = painterResource(id = R.drawable.minus_svgrepo_com),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp),

                    )
            }


            // Campo de texto que muestra el valor actual
            TextField(
                value = value.toString(),
                onValueChange = { newText ->
                    newText.toIntOrNull()?.let { newValue ->
                        onValueChange(newValue)
                    }
                },
                modifier = Modifier.width(80.dp).height(65.dp)
                    .padding(8.dp),
                textStyle =TextStyle(color = DarkBrown, fontSize = 18.sp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Beige,
                    focusedIndicatorColor = Brown,
                    unfocusedIndicatorColor = Beige,
                )
            )



            // Botón para incrementar el valor
            IconButton(onClick = { onValueChange(value + 1) }) {
                Image(
                    painter = painterResource(id = R.drawable.plus_svgrepo_com),
                    contentDescription = "Home",
                    modifier = Modifier.size(20.dp),

                )
            }

        }

    }
}
