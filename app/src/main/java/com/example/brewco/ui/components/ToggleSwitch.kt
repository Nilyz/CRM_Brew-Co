package com.example.brewco.ui.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.brewco.ui.theme.*

@Composable
fun ToggleSwitch() {

    var checked by remember { mutableStateOf(true) }
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Cream,
            uncheckedThumbColor = Brown,
            checkedTrackColor = Brown,
            uncheckedTrackColor = Cream
        )

    )
}