package com.example.simondice.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simondice.Model.Datos

class MyViewModel(): ViewModel() {
    // TAG
    private val TAG_LOG = "viewModel-DEBUG"

    // Variables observer
    val nombreLiveData: MutableLiveData<MutableState<String>> = MutableLiveData(Datos.nombre)
    val counterLiveData: MutableLiveData<MutableIntState> = MutableLiveData(Datos.counter)

    // Constructor
    init {
        Log.d(TAG_LOG,"viewModel iniciando")
    }

}