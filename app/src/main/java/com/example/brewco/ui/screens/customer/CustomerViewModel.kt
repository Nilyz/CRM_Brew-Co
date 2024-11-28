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
}
