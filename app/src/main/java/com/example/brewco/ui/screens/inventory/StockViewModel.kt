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
    val products: StateFlow<List<Product>> = _products //versión pública y de sólo lectura del _products para mostrar en UI

    //método de ViewModel que se ejecuta al crear una instancia de StockViewModel(tipo para que se cargue los productos)
    init {
        loadProducts()
    }

    fun loadProducts() {
        //usar una corutina para que getProducts haga petición asíncrona sin molestar
        viewModelScope.launch {
            val productsFromRepo = productRepository.getProducts()
            _products.value = productsFromRepo
        }
    }

    /*---------------CREAR NUEVO PRODUCTO PULSADO-----------------------*/
    fun addProduct(product: Product, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val isAdded = productRepository.addProduct(product)
            if (isAdded) {
                loadProducts()
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
                val fetchedProduct = productRepository.getProductById(productId)
                onProductFetched(fetchedProduct)
            } catch (e: Exception) {
                onProductFetched(null)
            }
        }
    }

    /*---------------EDITAR PRODUCTO-----------------------*/
    fun updateProduct(product: Product, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val isUpdated = productRepository.updateProduct(product)
                if (isUpdated) {
                    loadProducts()
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
                val isDeleted = productRepository.deleteProduct(productId)
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