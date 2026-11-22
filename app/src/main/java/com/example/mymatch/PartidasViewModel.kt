package com.example.mymatch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PartidasViewModel : ViewModel() {

    var uiState by mutableStateOf(PartidasUiState())
        private set

    init {
        uiState = uiState.copy(partidas = PartidaRepository.getPartidas())
    }

    fun onPartidaClick(partida: Partida) {
        uiState = uiState.copy(idSeleccionado = partida.id)
    }

    fun onVolver() {
        uiState = uiState.copy(idSeleccionado = null)
    }

    fun onBorrarClick(partida: Partida) {
        uiState = uiState.copy(partidaABorrar = partida)
    }

    fun onCancelarBorrado() {
        uiState = uiState.copy(partidaABorrar = null)
    }

    fun onConfirmarBorrado() {
        val partida = uiState.partidaABorrar ?: return
        PartidaRepository.deletePartida(partida.id)
        uiState = uiState.copy(
            partidas = PartidaRepository.getPartidas(),
            partidaABorrar = null,
            idSeleccionado = null
        )
    }

    fun onAnadirClick() {
        uiState = uiState.copy(mostrarDialogoAlta = true)
    }

    fun onCerrarDialogoAlta() {
        uiState = uiState.copy(mostrarDialogoAlta = false)
    }

    fun onGuardarNueva(
        juego: String,
        fecha: String,
        modo: String,
        resultado: String,
        marcador: String,
        duracion: String,
        comentario: String
    ) {
        val nueva = Partida(
            juego = juego,
            fecha = fecha,
            modo = modo,
            resultado = resultado,
            marcador = marcador,
            duracion = duracion,
            comentario = comentario
        )
        PartidaRepository.addPartida(nueva)
        uiState = uiState.copy(
            partidas = PartidaRepository.getPartidas(),
            mostrarDialogoAlta = false
        )
    }

    fun onEditarClick(partida: Partida) {
        uiState = uiState.copy(partidaAEditar = partida)
    }

    fun onCerrarDialogoEdicion() {
        uiState = uiState.copy(partidaAEditar = null)
    }

    fun onGuardarEdicion(
        id: String,
        juego: String,
        fecha: String,
        modo: String,
        resultado: String,
        marcador: String,
        duracion: String,
        comentario: String
    ) {
        val partida = PartidaRepository.getPartidaById(id) ?: return
        val editada = partida.copy(
            juego = juego,
            fecha = fecha,
            modo = modo,
            resultado = resultado,
            marcador = marcador,
            duracion = duracion,
            comentario = comentario
        )
        PartidaRepository.updatePartida(editada)
        uiState = uiState.copy(
            partidas = PartidaRepository.getPartidas(),
            partidaAEditar = null
        )
    }
}