package com.example.simondice.Model

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

// Datos que serán observados por el controller (MyViewModel)
object Datos {
    val text = mutableStateOf("")
    val counter = mutableIntStateOf(0)
    val secuencia = mutableListOf<Int>()
    val playerGuess = mutableListOf<Int>()
    val historico = mutableIntStateOf(0)
}

// Corutinas de ejemplo
enum class Corutinas(val start_activo: Boolean, val boton_activo: Boolean) {
    INICIO(start_activo = true, boton_activo = false),
    GENERANDO(start_activo = false, boton_activo = false),
    ADIVINANDO(start_activo = false, boton_activo = true)
}