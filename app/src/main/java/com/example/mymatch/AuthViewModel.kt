package com.example.mymatch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var uiState by mutableStateOf(AuthUiState())
        private set

    init {
        uiState = uiState.copy(
            sesionIniciada = AuthRepository.haySesion(),
            correo = AuthRepository.correoActual()
        )
    }

    fun onIrARegistro() {
        uiState = uiState.copy(pantalla = PantallaAcceso.REGISTRO, error = null, mensaje = null)
    }

    fun onIrARecuperar() {
        uiState = uiState.copy(pantalla = PantallaAcceso.RECUPERAR, error = null, mensaje = null)
    }

    fun onVolverALogin() {
        uiState = uiState.copy(pantalla = PantallaAcceso.LOGIN, error = null, mensaje = null)
    }

    fun onEntrar(email: String, clave: String) {
        uiState = uiState.copy(cargando = true, error = null)
        AuthRepository.iniciarSesion(email, clave) { error ->
            uiState = if (error == null) {
                uiState.copy(cargando = false, sesionIniciada = true, correo = AuthRepository.correoActual())
            } else {
                uiState.copy(cargando = false, error = error)
            }
        }
    }

    fun onRegistrar(email: String, clave: String) {
        uiState = uiState.copy(cargando = true, error = null)
        AuthRepository.registrar(email, clave) { error ->
            uiState = if (error == null) {
                uiState.copy(cargando = false, sesionIniciada = true, correo = AuthRepository.correoActual())
            } else {
                uiState.copy(cargando = false, error = error)
            }
        }
    }

    fun onEnviarRecuperacion(email: String) {
        uiState = uiState.copy(cargando = true, error = null, mensaje = null)
        AuthRepository.recuperarClave(email) { error ->
            uiState = if (error == null) {
                uiState.copy(cargando = false, mensaje = "Si el correo existe, recibirás un enlace para cambiar la contraseña")
            } else {
                uiState.copy(cargando = false, error = error)
            }
        }
    }

    fun onCerrarSesion() {
        AuthRepository.cerrarSesion()
        uiState = AuthUiState()
    }
}