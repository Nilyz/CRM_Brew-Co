package com.example.brewco.ui.screens.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewco.data.model.Alert
import com.example.brewco.data.model.Product
import com.example.brewco.data.repository.AlertRepository
import com.example.brewco.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// NotificationViewModel
class NotificationViewModel : ViewModel() {
    private val alertRepository = AlertRepository()
    private val _alerts = MutableStateFlow<List<Alert>>(emptyList())
    val alerts: StateFlow<List<Alert>> = _alerts

    // Obtener alertas desde el repositorio
    fun getAlertByPrompt() {
        viewModelScope.launch {
            try {
                val fetchedAlerts = alertRepository.getAlerts()
                _alerts.value = fetchedAlerts // Actualizamos el estado de alertas
            } catch (e: Exception) {
                // Si hay un error, se podrían manejar los casos, o registrar el error
                _alerts.value = emptyList()
            }
        }
    }
}

