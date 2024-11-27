package com.example.simondice.View

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.Model.Colores
import com.example.simondice.ViewModel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UI(myViewModel: MyViewModel) {
    // Estructura principal
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        StartButton(myViewModel) // Botón de inicio
        Secuence(myViewModel)    // Componente de secuencia (suponiendo que está implementado)
    }
}
@Composable
fun StartButton(myViewModel: MyViewModel) {
    // Obtengo el contexto desde Compose
    val context = LocalContext.current

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
        // Título del juego
        Text(
            text = "SIMON DICE",
            fontSize = 40.sp, // Incrementa el tamaño del texto
            fontWeight = FontWeight.Bold, // Añade negrita para mayor énfasis
            color = Color(0xFF6200EA), // Cambia el color a un morado vibrante
            textAlign = TextAlign.Center, // Centra el texto
            modifier = Modifier
                .fillMaxWidth() // Asegura que el texto ocupe todo el ancho disponible
                .padding(vertical = 50.dp) // Reduce el espacio vertical
        )

        // Botón de inicio
        TextButton(
            enabled = estado,
            onClick = {
                // Lógica del botón
            },
            modifier = Modifier
                .padding(20.dp) // Más espacio alrededor del botón
                .size(150.dp, 150.dp) // Botón más grande para destacar
                .shadow(elevation = 8.dp, shape = CircleShape) // Añade una sombra y forma circular
                .clip(CircleShape), // Recorta el botón en forma circular
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF03DAC5), // Color turquesa llamativo
                contentColor = Color.White // Texto en blanco para buen contraste
            )
        ) {
            Text(
                text = "INICIAR\n(ronda ${ronda.intValue})", // Formato más limpio del texto
                fontSize = 18.sp, // Aumenta ligeramente el tamaño del texto
                fontWeight = FontWeight.Bold, // Negrita para mejor visibilidad
                textAlign = TextAlign.Center // Centra el texto dentro del botón
            )
        }


        // Aquí puedes agregar otros componentes o estructuras
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                // Columna 1: Botones u otros elementos
                BotoNormal(color = Colores.BROWN, context = context, myViewModel = myViewModel)
                BotoNormal(color = Colores.PINK, context = context, myViewModel = myViewModel)
            }
            Column {
                // Columna 2: Botones u otros elementos
                BotoNormal(color = Colores.GRAY, context = context, myViewModel = myViewModel)
                BotoNormal(color = Colores.PURPLE, context = context, myViewModel = myViewModel)
            }
        }
    }
}

@Composable
fun BotoNormal(
    color: Colores, // Objeto que contiene información del botón (nombre, id, color, etc.)
    context: Context, // Contexto para operaciones con ViewModel
    myViewModel: MyViewModel, // ViewModel para la lógica de negocio
) {
    // Variable observer
    var estado by remember { mutableStateOf(myViewModel.estadoLiveData.value!!.boton_activo) }

    // Set context
    myViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        estado = myViewModel.estadoLiveData.value!!.boton_activo
    }

    // Definición del botón
    Button(
        enabled = estado, // Estado del botón (activo/inactivo)
        onClick = {
            // Se pasan los datos de la seleccion del jugador al viewModel
            myViewModel.procesarClick(color.id,context)
        },
        modifier = Modifier
            .padding(10.dp) // Margen externo del botón
            .size(150.dp, 100.dp), // Dimensiones del botón
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray, // Color de fondo del botón
            contentColor = Color.White // Color del texto del botón
        )
    ) {
        Text(text = "..."+color.nombre)
    }
}

@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun Secuence(myViewModel: MyViewModel) {
    // Colores de los botones que cambiarán durante el juego
    val redButtonColor = remember { mutableStateOf(Colores.RED.color) }
    val blueButtonColor = remember { mutableStateOf(Colores.BLUE.color) }
    val greenButtonColor = remember { mutableStateOf(Colores.GREEN.color) }
    val yellowButtonColor = remember { mutableStateOf(Colores.YELLOW.color) }

    // Observar el estado actual de la variable 'isPrinted' desde el ViewModel
    var isPrinted by remember { mutableStateOf(myViewModel.currentLiveData.value ?: false) }

    // Observador para 'currentLiveData', actualiza 'isPrinted' cuando cambie
    myViewModel.currentLiveData.observe(LocalLifecycleOwner.current) { value ->
        isPrinted = value ?: false
    }

    // Observar el estado de los botones activos desde el ViewModel
    var estado by remember { mutableStateOf(myViewModel.estadoLiveData.value?.boton_activo ?: false) }

    // Observador para 'estadoLiveData', actualiza 'estado' cuando cambie
    myViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) { value ->
        estado = value?.boton_activo ?: false
    }

    // Observar el estado de "start_activo" desde el ViewModel
    var estadoStart by remember { mutableStateOf(myViewModel.estadoLiveData.value?.start_activo ?: false) }

    // Observador para "start_activo", actualiza 'estadoStart' cuando cambie
    myViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) { value ->
        estadoStart = value?.start_activo ?: false
    }

    // Observar la secuencia de colores desde el ViewModel
    var secuencia by remember { mutableStateOf(myViewModel.secuenciaLiveData.value ?: listOf()) }

    // Observador para 'secuenciaLiveData', actualiza 'secuencia' cuando cambie
    myViewModel.secuenciaLiveData.observe(LocalLifecycleOwner.current) { value ->
        secuencia = value ?: listOf()
    }

    // Función suspendida que maneja la secuencia de colores, añadiendo retrasos entre cada color
    suspend fun colorearSecuencia() {
        // Cambiar el estado de 'isPrinted' para indicar que se está mostrando la secuencia
        isPrinted = true
        for (colorId in secuencia) {
            // Pausar por 300ms para crear una animación de cambio de color
            delay(300)
            // Cambiar el color del botón correspondiente según el color de la secuencia
            when (colorId) {
                Colores.RED.id -> {
                    redButtonColor.value = Colores.ORANGE.colorPressed
                    delay(1000)  // Mantener el color presionado por un segundo
                    redButtonColor.value = Colores.ORANGE.color
                }
                Colores.BLUE.id -> {
                    blueButtonColor.value = Colores.CYAN.colorPressed
                    delay(1000)
                    blueButtonColor.value = Colores.CYAN.color
                }
                Colores.GREEN.id -> {
                    greenButtonColor.value = Colores.GREEN.colorPressed
                    delay(1000)
                    greenButtonColor.value = Colores.GREEN.color
                }
                Colores.YELLOW.id -> {
                    yellowButtonColor.value = Colores.PURPLE.colorPressed
                    delay(1000)
                    yellowButtonColor.value = Colores.PURPLE.color
                }
            }
        }
        // Cambiar el estado del juego a "jugando" al finalizar la secuencia
        myViewModel.setJugando()
    }

    // Obtener el alcance de la corrutina, para poder ejecutar 'colorearSecuencia'
    val coroutineScope = rememberCoroutineScope()

    // Si el juego está en estado activo, 'start_activo' es falso y la secuencia aún no se ha mostrado
    if (estado && !estadoStart && isPrinted == false) {
        coroutineScope.launch {
            colorearSecuencia()  // Iniciar la secuencia de colores
        }
    }
}

