package com.example.brewco.ui.screens.customer

import CustomerRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewco.data.model.Client
import com.example.brewco.data.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomerViewModel : ViewModel() {
    private val customerRepository = CustomerRepository()

    // Cambio de tipo a List<Client>
    private val _customers = MutableStateFlow<List<Client>>(emptyList())
    val customers: StateFlow<List<Client>> = _customers

    // Cargar los clientes desde el repositorio
    init {
        getClients()
    }

    private fun getClients() {
        viewModelScope.launch {
            val clientsList = customerRepository.getClients()
            _customers.value = clientsList
        }
    }

    /*---------------CREAR NUEVO CLIENTE PULSADO-----------------------*/
    fun addClient(client: Client, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val isAdded = customerRepository.addClient(client)
            if (isAdded) {
                getClients()
                onSuccess()
            } else {
                onError("Error al añadir el cliente")
            }
        }
    }


    /*---------------OBTENER CLIENTE POR ID-----------------------*/
    fun getClienteById(clientId: String, onClientFetched: (Client?) -> Unit) {
        viewModelScope.launch {
            try {
                val fetchedClient = customerRepository.getClientById(clientId)
                onClientFetched(fetchedClient)
            } catch (e: Exception) {
                onClientFetched(null)
            }
        }
    }
    /*---------------EDITAR CLIENTE-----------------------*/
    fun updateClient(client: Client, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isUpdated = customerRepository.updateClient(client) // Llamada al repositorio
                if (isUpdated) {
                    getClients() // Refrescar la lista
                    onSuccess()
                } else {
                    onError("Error al actualizar el cliente")
                }
            } catch (e: Exception) {
                onError("Error al actualizar el cliente: ${e.message}")
            }
        }
    }

    /*---------------ELIMINAR CLIENTE-----------------------*/
    fun deleteClient(clientId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isDeleted = customerRepository.deleteClient(clientId) // Llamar al repositorio
                if (isDeleted) {
                    getClients()
                    onSuccess()
                } else {
                    onError("Error al eliminar el cliente")
                }
            } catch (e: Exception) {
                onError("Error al eliminar el cliente: ${e.message}")
            }
        }
    }


}
