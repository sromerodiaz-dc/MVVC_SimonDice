package com.example.simondice.View

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
/*
@Composable
fun unaTostada() {

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/


@Composable
fun MisBotones(){

    // Variables observadas...


    Column {
        // mostrar el contador de clics
        TextButton(
            onClick = {
                Log.d("botones", "click!")
                /*counter++
                pulsaciones.add(Colores.ROJO.valor)

                colores_pulsados.add(Colores.ROJO.nombre)

                Log.d("botones", colores_pulsados.toString())*/
            }) {
            //Text("CLICS: para el log y $counter")
        }
/*
        // mientras no tecleamos mas de tres caracteres no se muestra el saludo
        if (name.value.length > 3) {
            Text(
                text = "Nombre: ${name.value}!",
                fontSize = 24.sp
            )
        }
        // campo de texto para rellenar
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            label = { Text(text = "Name") }
        )*/
    }
}
/*
@Preview(showBackground = true)
@Composable
fun myPreview() {

}*/