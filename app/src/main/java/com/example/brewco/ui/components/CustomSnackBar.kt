package com.example.brewco.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.brewco.ui.theme.Brown
import com.example.brewco.ui.theme.Cream
import com.example.brewco.ui.theme.DarkBrown
import kotlinx.coroutines.launch


@Composable
fun CustomSnackBar(
    snackbarHostState: SnackbarHostState,
) {
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) { snackbarData ->
        Snackbar(
            snackbarData = snackbarData,
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            containerColor = Cream,
            contentColor = Brown
        )
    }
}

@Composable
fun SnackbarMessageHandler(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    addedMessageKey: String = "added",
    deleteMessageKey: String = "delete",
    editedMessageKey: String = "edited",
    element: String
) {
    val addedMessage =
        navHostController.currentBackStackEntry?.arguments?.getString(addedMessageKey)
    val deleteMessage =
        navHostController.currentBackStackEntry?.arguments?.getString(deleteMessageKey)
    val editedMessage =
        navHostController.currentBackStackEntry?.arguments?.getString(editedMessageKey)

    val scope = rememberCoroutineScope()

    LaunchedEffect(addedMessage, deleteMessage, editedMessage) {
        when {
            addedMessage == "true" -> {
                scope.launch {
                    snackbarHostState.showSnackbar("${element} añadido con éxito!")
                }
            }

            deleteMessage == "true" -> {
                scope.launch {
                    snackbarHostState.showSnackbar("${element} eliminado con éxito!")
                }
            }

            editedMessage == "true" -> {
                scope.launch {
                    snackbarHostState.showSnackbar("${element} editado con éxito!")
                }
            }
        }
    }
}