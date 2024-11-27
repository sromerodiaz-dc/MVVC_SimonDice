package com.example.simondice.Model

import android.graphics.Color
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

// Datos que serán observados por el controller (MyViewModel)
object Datos {
    var nombre = mutableStateOf("")
    var counter = mutableIntStateOf(0)
}

enum class Colores(val color: Int, val txt: String) {
    ROJO(color = Color.RED, txt = "rojo"),
    VERDE(color = Color.GREEN, txt = "verde"),
    AZUL(color = Color.BLUE, txt = "azul"),
    AMARILLO(color = Color.YELLOW, txt = "amarillo")
}

// Corutinas de ejemplo
enum class Corutinas(val start_activo: Boolean, val boton_activo: Boolean) {
    INICIO(start_activo = true, boton_activo = false),
    GENERANDO(start_activo = false, boton_activo = false),
    ADIVINANDO(start_activo = false, boton_activo = true)
}