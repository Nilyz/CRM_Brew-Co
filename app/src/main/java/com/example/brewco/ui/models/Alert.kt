package com.example.brewco.ui.models

import java.time.LocalDate

data class Alert(

    var tipo: String = "",
    var fecha: LocalDate? = null,
    var descripcion: String = "",
    var hora: String = "",

)
