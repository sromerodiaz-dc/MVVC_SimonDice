package com.example.simondice.ViewModel

import android.util.Log
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
    val historicoLiveData: MutableLiveData<MutableState<Int>> = MutableLiveData(Datos.historico)

    // Constructor
    init {
        Log.d(TAG_LOG,"viewModel iniciando")
    }

}