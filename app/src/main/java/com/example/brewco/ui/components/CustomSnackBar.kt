package com.example.brewco.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

@Composable
fun CustomSnackbar(
    snackbarHostState: SnackbarHostState,
    message: String
) {
    LaunchedEffect(message) {
        if (message.isNotEmpty()) {
            snackbarHostState.showSnackbar(message)
        }
    }

    SnackbarHost(hostState = snackbarHostState,modifier = androidx.compose.ui.Modifier
        .fillMaxWidth()
        .padding(top = 16.dp))
}
