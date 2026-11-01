package com.example.mymatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.mymatch.ui.theme.MyMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyMatchTheme {
                AppPantallas()
            }
        }
    }
}

@Composable
fun AppPantallas() {
    var pantalla by rememberSaveable { mutableStateOf("lista") }
    var juegoId by rememberSaveable { mutableStateOf(0) }

    when (pantalla) {
        "lista" -> PantallaLista(
            juegos = juegosEjemplo,
            onJuegoClick = { juego ->
                juegoId = juego.id
                pantalla = "detalle"
            }
        )
        "detalle" -> juegosEjemplo.find { it.id == juegoId }?.let { juego ->
            PantallaDetalle(
                juego = juego,
                onVolver = { pantalla = "lista" }
            )
        }
    }
}
