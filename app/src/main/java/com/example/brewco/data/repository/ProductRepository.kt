package com.example.brewco.data.repository

import com.google.firebase.firestore.FirebaseFirestore

//CRUD PARA PRODUCTOS
class ProductoRepository {
    private val db = FirebaseFirestore.getInstance()
    private val productosRef = db.collection("productos")

    fun agregarProducto(){

    }
    fun obtenerProductos(){

    }
    fun actualizarProducto(){

    }
    fun eliminarProducto(){

    }
}