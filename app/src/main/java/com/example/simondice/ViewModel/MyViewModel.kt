package com.example.simondice.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.collection.MutableIntList
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simondice.Model.Corutinas
import com.example.simondice.Model.Datos

class MyViewModel(): ViewModel() {
    // TAG
    private val TAG_LOG = "viewModel-DEBUG"

    // Variables observables
    val textLiveData: MutableLiveData<MutableState<String>> = MutableLiveData(Datos.text)
    val counterLiveData: MutableLiveData<MutableIntState> = MutableLiveData(Datos.counter)
    val estadoLiveData: MutableLiveData<Corutinas> = MutableLiveData(Corutinas.INICIO)
    val secuenciaLiveData: MutableLiveData<MutableList<Int>> = MutableLiveData(Datos.secuencia)
    val playerGuessLiveData: MutableLiveData<MutableList<Int>> = MutableLiveData(Datos.playerGuess)
    val currentLiveData: MutableLiveData<MutableState<Boolean>> = MutableLiveData(Datos.current)

    // Constructor
    init {
        Log.d(TAG_LOG,"viewModel iniciando")
    }

    private fun incrementarContador() {
        // Se incrementa el contador, asignando 0 si es nulo
        counterLiveData.value?.intValue = counterLiveData.value?.intValue?.inc() ?: 0
    }

    private fun generarNuevaSecuencia() {
        // Aumenta el contador
        incrementarContador()

        // Añade un número aleatorio (entre 0 y 3) a la secuencia
        val nuevoNumero = (0..3).random()
        secuenciaLiveData.value?.add(nuevoNumero)

        // Reinicia el estado de juego actual
        currentLiveData.value?.value = false
        setJugando()
    }

    public fun procesarClick(botonId: Int, contexto: Context) {
        // Añade el botón seleccionado a la lista de suposiciones
        playerGuessLiveData.value?.add(botonId)

        // Realiza la comprobación de la secuencia
        verificarSecuencia(contexto)
    }

    private fun verificarSecuencia(contexto: Context) {
        // Solo se realiza la comprobación si las suposiciones no exceden la longitud de la secuencia
        if ((playerGuessLiveData.value?.size ?: 0) <= (secuenciaLiveData.value?.size ?: 0)) {
            validarSecuencia(contexto)
        }
    }

    private fun validarSecuencia(contexto: Context) {
        // Verifica si la secuencia del jugador coincide completamente con la secuencia generada
        if (secuenciaLiveData.value == playerGuessLiveData.value) {
            reiniciarJuego(contexto, "Ronda ${counterLiveData.value} superada")
            generarNuevaSecuencia()
        }
        // Si la secuencia del jugador coincide con una parte de la secuencia
        else if (secuenciaLiveData.value?.take(playerGuessLiveData.value?.size ?: 0) == playerGuessLiveData.value) {
            // Mensaje de que va por buen camino
            Log.d("TAG", "CORRECTO")
            // Aquí podría añadirse un mensaje para el jugador (opcional)
            // setToastText("Vas por buen camino!!")
        }
        // Si la secuencia es incorrecta
        else {
            reiniciarJuego(contexto, "Ronda perdida :(")
        }
    }

    private fun reiniciarJuego(contexto: Context, mensaje: String) {
        // Reinicia las secuencias y el contador, y muestra un mensaje de estado
        playerGuessLiveData.value?.clear()
        secuenciaLiveData.value?.clear()
        counterLiveData.value?.intValue = 0

        Log.d("TAG", "INCORRECTO")
        mostrarMensajeToast(contexto, mensaje)
        setEsperando()
    }

    private fun mostrarMensajeToast(contexto: Context, mensaje: String) {
        // Actualiza el texto del Toast y lo muestra
        setToastText(mensaje)
        Toast.makeText(contexto, textLiveData.value?.value, Toast.LENGTH_SHORT).show()
    }

    private fun setToastText(mensaje: String) {
        // Establece el texto que se mostrará en el Toast
        textLiveData.value?.value = mensaje
    }

    // Setters estado de juego
    public fun setJugando() {
        estadoLiveData.value = Corutinas.GENERANDO
    }

    private fun setAdivinando() {
        estadoLiveData.value = Corutinas.ADIVINANDO
    }

    private fun setEsperando() {
        estadoLiveData.value = Corutinas.INICIO
    }
}