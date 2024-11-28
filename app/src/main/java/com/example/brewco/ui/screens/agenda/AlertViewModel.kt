package com.example.brewco.ui.screens.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewco.data.model.Alert
import com.example.brewco.data.repository.AlertRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlertViewModel : ViewModel() {
    private val alertRepository = AlertRepository()

    private val _alerts = MutableStateFlow<List<Alert>>(emptyList())
    val alerts: StateFlow<List<Alert>> = _alerts // Versión pública y de solo lectura de las alertas

    private val _todayAlerts = MutableStateFlow<List<Alert>>(emptyList())
    val todayAlerts: StateFlow<List<Alert>> = _todayAlerts // Para alertas activas del día actual

    // Método de inicialización para cargar todas las alertas
    init {
        loadAlerts()
    }

    // Cargar todas las alertas
    fun loadAlerts() {
        viewModelScope.launch {
            val alertsFromRepo = alertRepository.getAlerts()
            _alerts.value = alertsFromRepo
        }
    }

    // Cargar alertas activas para hoy
    fun loadTodayAlerts() {
        viewModelScope.launch {
            val todayAlertsFromRepo = alertRepository.getAlertsForToday()
            _todayAlerts.value = todayAlertsFromRepo
        }
    }

    /*---------------CREAR NUEVA ALERTA-----------------------*/
    fun addAlert(alert: Alert, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val isAdded = alertRepository.addAlert(alert)
            if (isAdded) {
                loadAlerts()
                onSuccess() // Acción en caso de éxito
            } else {
                onError("Error al añadir la alerta")
            }
        }
    }

    /*---------------OBTENER ALERTA POR ID-----------------------*/
    fun getAlertById(alertId: String, onAlertFetched: (Alert?) -> Unit) {
        viewModelScope.launch {
            try {
                val fetchedAlert = alertRepository.getAlertById(alertId)
                onAlertFetched(fetchedAlert)
            } catch (e: Exception) {
                onAlertFetched(null)
            }
        }
    }

    /*---------------EDITAR ALERTA-----------------------*/
    fun updateAlert(alert: Alert, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isUpdated = alertRepository.updateAlert(alert)
                if (isUpdated) {
                    loadAlerts()
                    onSuccess() // Acción en caso de éxito
                } else {
                    onError("Error al actualizar la alerta")
                }
            } catch (e: Exception) {
                onError("Error al actualizar la alerta: ${e.message}")
            }
        }
    }

    /*---------------ELIMINAR ALERTA-----------------------*/
    fun deleteAlert(alertId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isDeleted = alertRepository.deleteAlert(alertId)
                if (isDeleted) {
                    loadAlerts()
                    onSuccess() // Acción en caso de éxito
                } else {
                    onError("Error al eliminar la alerta")
                }
            } catch (e: Exception) {
                onError("Error al eliminar la alerta: ${e.message}")
            }
        }
    }
}
