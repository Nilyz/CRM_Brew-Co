package com.example.brewco.data.repository

import com.example.brewco.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

//CRUD PARA PRODUCTOS
class ProductRepository {
    private val db = FirebaseFirestore.getInstance()
    private val productsCollection = db.collection("productos")

    suspend fun addProduct(producto: Product): Boolean {
        return try {
            productsCollection.add(producto).await()
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun getProducts(): List<Product> {
        return try {
            val snapshot = productsCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject<Product>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList() // Devuelve una lista vacía en caso de error
        }
    }

    suspend fun getProductById(productId: String): Product? {
        return try {
            val documentSnapshot = db.collection("productos")
                .document(productId)
                .get()
                .await()

            if (documentSnapshot.exists()) {
                documentSnapshot.toObject(Product::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


    suspend fun updateProduct(producto: Product): Boolean {
        return try {
            productsCollection.document(producto.id).set(producto).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun deleteProduct(productId: String): Boolean {
        return try {
            productsCollection.document(productId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }
}