package com.example.brewco.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.brewco.ui.theme.Brown

@Composable
fun ErrorSnackbar(
    message: String, // Mensaje de error dinámico
    isVisible: Boolean, // Controla si se muestra o no
    onDismiss: () -> Unit // Acción al ocultar el mensaje
) {
    // Animación de visibilidad
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(), // Aparece de abajo hacia arriba
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(), // Desaparece hacia abajo
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter // Centra el Card en la parte inferior
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = Brown)
            ) {
                Text(
                    text = message,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center, // Centra el texto horizontalmente
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }


    // Desaparece automáticamente después de 3 segundos
    LaunchedEffect(isVisible) {
        if (isVisible) {
            kotlinx.coroutines.delay(3000)
            onDismiss()
        }
    }
}
