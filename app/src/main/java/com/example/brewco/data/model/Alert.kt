package com.example.brewco.data.model

import java.time.LocalDate

data class Alert(

    val titulo: String = "",
    val fecha: String = "",
    val inicio: String = "",
    val fin: String = "",
    val aviso: Boolean = false,
    val empleado: String = "",
    val descripcion: String = "",
)