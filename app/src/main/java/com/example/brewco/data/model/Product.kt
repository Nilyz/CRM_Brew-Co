package com.example.brewco.data.model

data class Product(
    val id:String="",
    val nombre:String="",
    val categoria: String = "",
    val stock: Int = 0,
    val precio: Double = 0.0
)