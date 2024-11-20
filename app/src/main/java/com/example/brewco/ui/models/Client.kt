package com.example.brewco.ui.models

import java.time.LocalDate

data class Client(

    var nombre: String = "",
    var apellidos: String = "",
    var telefono: String = "",
    var ultCompra: String = "",
    var puntos: Int = 0

)
