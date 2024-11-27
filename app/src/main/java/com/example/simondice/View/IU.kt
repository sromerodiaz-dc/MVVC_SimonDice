package com.example.simondice.View

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.Model.Datos
import com.example.simondice.ViewModel.MyViewModel

@Composable
fun StartButton(myViewModel: MyViewModel) {
    // Variable observer
    var estado by remember { mutableStateOf(myViewModel.estadoLiveData.value!!.boton_activo) }

    // Set context
    myViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        estado = myViewModel.estadoLiveData.value!!.start_activo
    }

    // Variable observer
    var ronda by remember { mutableStateOf(myViewModel.counterLiveData.value!!) }

    // Set context
    myViewModel.counterLiveData.observe(LocalLifecycleOwner.current) {
        ronda = myViewModel.counterLiveData.value!!
    }

    // Composición principal
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Título
        Text(
            text = "SIMON DICE",
            fontSize = 30.sp,
            modifier = Modifier.padding(vertical = 100.dp)
        )

        // Botón de inicio
        TextButton(
            enabled = estado,
            onClick = {
                // Lógica del botón aun por hacer
            },
            modifier = Modifier
                .padding(10.dp)
                .size(100.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White
            )
        ) {
            Text(text = "INICIAR\n(ronda número: $ronda!)")
        }

        // Aquí puedes agregar otros componentes o estructuras
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                // Columna 1: Botones u otros elementos
            }
            Column {
                // Columna 2: Botones u otros elementos
            }
        }
    }
}
