package com.example.simondice.Model

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

// Datos que serán observados por el controller (MyViewModel)
object Datos {
    val text = mutableStateOf("")
    val counter = mutableIntStateOf(0)
    val secuencia = mutableListOf<Int>()
    val playerGuess = mutableListOf<Int>()
    val current = mutableStateOf(false)
}

// Colores con ID para botones
enum class Colores(val id: Int, val nombre: String, val color: Color, val colorPressed: Color) {
    // Rojos
    RED(0, "Red", Color(0xFFE57373), Color(0xFFB71C1C)),
    // Azules
    BLUE(1, "Blue", Color(0xFF64B5F6), Color(0xFF0D47A1)),
    // Verdes
    GREEN(2, "Green", Color(0xFF81C784), Color(0xFF1B5E20)),
    // Amarillos
    YELLOW(3, "Yellow", Color(0xFFFFF176), Color(0xFFF57F17)),
    // Naranjas
    ORANGE(4, "Orange", Color(0xFFFFB74D), Color(0xFFE65100)),
    // Violetas
    PURPLE(5, "Purple", Color(0xFFBA68C8), Color(0xFF4A148C)),
    // Cian
    CYAN(6, "Cyan", Color(0xFF4DD0E1), Color(0xFF006064)),
    // Rosa
    PINK(7, "Pink", Color(0xFFF06292), Color(0xFF880E4F)),
    // Marrones
    BROWN(8, "Brown", Color(0xFFA1887F), Color(0xFF3E2723)),
    // Gris
    GRAY(9, "Gray", Color(0xFFE0E0E0), Color(0xFF424242)),
}


// Corutinas de ejemplo
enum class Corutinas(val start_activo: Boolean, val boton_activo: Boolean) {
    INICIO(start_activo = true, boton_activo = false),
    GENERANDO(start_activo = false, boton_activo = false),
    ADIVINANDO(start_activo = false, boton_activo = true)
}