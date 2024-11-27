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