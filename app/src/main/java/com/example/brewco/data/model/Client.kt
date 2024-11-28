package com.example.brewco.data.model

data class Client(

    var nombre: String = "",
    var apellido: String = "",
    var telefono: String = "",
    var ultCompra: String = "",
    var puntos: Int = 0,
    var correo: String = "",
    var notas: String = ""
)