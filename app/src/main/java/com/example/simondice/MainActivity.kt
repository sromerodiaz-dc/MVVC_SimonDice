package com.example.simondice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.simondice.View.UI
import com.example.simondice.ViewModel.MyViewModel
import com.example.simondice.ui.theme.SimonDiceTheme

/**
 * Código refactorizado del proyecto de PMDM => Simon Dice
 *
 * @author Santiago Romero
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG_LOG = "mainDebug"
        Log.d(TAG_LOG, "App iniciada")

        enableEdgeToEdge()

        val myViewModel = MyViewModel()
        setContent {
            SimonDiceTheme {
                UI(myViewModel = myViewModel)
            }
        }
    }
}

