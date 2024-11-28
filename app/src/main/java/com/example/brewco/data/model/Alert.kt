package com.example.brewco.data.model

import java.time.LocalDate

data class Alert(

    val id:String="",
    val titulo: String = "",
    val fechaInicio: String = "",
    val horaInicio: String = "",
    val minutosInicio: String = "",
    val fechaFin: String = "",
    val horaFin: String = "",
    val minutosFin: String = "",
    val aviso: Boolean = false,
    val empleado: String = "",
    val descripcion: String = "",
)