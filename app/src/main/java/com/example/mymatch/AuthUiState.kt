package com.example.mymatch

enum class PantallaAcceso { LOGIN, REGISTRO, RECUPERAR }

data class AuthUiState(
    val sesionIniciada: Boolean = false,
    val correo: String = "",
    val pantalla: PantallaAcceso = PantallaAcceso.LOGIN,
    val cargando: Boolean = false,
    val error: String? = null,
    val mensaje: String? = null
)