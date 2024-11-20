package com.example.brewco.data.model

import java.time.LocalDate

data class Alert(

    var tipo: String = "",
    var fecha: LocalDate? = null,
    var descripcion: String = "",
    var hora: String = ""
)