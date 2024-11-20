package com.example.brewco.data.repository

import com.example.brewco.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

//CRUD PARA PRODUCTOS
class ProductRepository {
    private val db = FirebaseFirestore.getInstance()
    private val productsCollection = db.collection("productos")


    suspend fun getProducts(): List<Product> {
        return try {
            val snapshot = productsCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject<Product>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList() 
        }
    }



}