package com.example.brewco.ui.screens.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewco.data.model.Product
import com.example.brewco.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//Gestiona el estado de los productos y maneja la interacción con el repositorio de producto

class StockViewModel : ViewModel() {
    private val productRepository = ProductRepository()

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products //versión pública y de sólo lectura del _products

    //método de ViewModel que se ejecuta al crear una instancia de StockViewModel
    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            val productsFromRepo = productRepository.getProducts()
            _products.value = productsFromRepo
        }
    }

    fun addProduct(product: Product, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val isAdded = productRepository.addProduct(product)
            if (isAdded) {
                onSuccess() // Añadir correctamente
            } else {
                onError("Error al añadir el producto")
            }
        }
    }
}