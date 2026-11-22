package com.example.mymatch

data class PartidasUiState(
    val partidas: List<Partida> = emptyList(),
    val idSeleccionado: String? = null,
    val partidaABorrar: Partida? = null,
    val mostrarDialogoAlta: Boolean = false,
    val partidaAEditar: Partida? = null
)