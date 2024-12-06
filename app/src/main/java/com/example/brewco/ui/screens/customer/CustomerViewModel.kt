package com.example.brewco.ui.screens.customer

import CustomerRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewco.data.model.Client
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

    /*---------------OBTENER CLIENTE POR ID-----------------------*/
    fun getClienteById(clientId: String, onClientFetched: (Client?) -> Unit) {
        viewModelScope.launch {
            try {
                val fetchedClient = customerRepository.getClientById(clientId)
                onClientFetched(fetchedClient)
            } catch (e: Exception) {
                onClientFetched(null)
            }
        }}
    /*---------------CREAR NUEVO PRODUCTO PULSADO-----------------------*/
    fun addClient(client: Client, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val isAdded = customerRepository.addClient(client)
            if (isAdded) {
                loadClient()
                onSuccess() // Añadir correctamente
            } else {
                onError("Error al añadir el producto")
            }
        }
    }

    /*---------------OBTENER PRODUCTO-----------------------*/
    fun getProductById(productId: String, onProductFetched: (Product?) -> Unit) {
        viewModelScope.launch {
            try {
                // Aquí realizar llamada suspendida al repositorio
                val fetchedClient = customerRepository.getClientById(clientid)
                onProductFetched(fetchedClient)
            } catch (e: Exception) {
                onProductFetched(null)
            }
        }
    }

    /*---------------EDITAR PRODUCTO-----------------------*/
    fun updateClient(client: Client, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isUpdated = customerRepository.updateClient(client)
                if (isUpdated) {
                    loadClient()
                    onSuccess()
                } else {
                    onError("Error al actualizar el producto")
                }
            } catch (e: Exception) {
                onError("Error al actualizar el producto: ${e.message}")
            }
        }
    }

    /*---------------ELIMINAR PRODUCTO-----------------------*/
    fun deleteProduct(productId: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isDeleted = customerRepositoryRepository.deleteProduct(productId)
                if (isDeleted) {
                    loadProducts()
                    onSuccess() // Acción al eliminar el producto correctamente
                } else {
                    onError("Error al eliminar el producto")
                }
            } catch (e: Exception) {
                onError("Error al eliminar el producto: ${e.message}")
            }
        }
    }
}
