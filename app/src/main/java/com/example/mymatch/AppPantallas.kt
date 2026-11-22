package com.example.mymatch

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppPantallas(
    viewModel: PartidasViewModel = viewModel()
) {
    val uiState = viewModel.uiState

    val partidaSeleccionada = uiState.partidas.find { it.id == uiState.idSeleccionado }

    BackHandler(enabled = partidaSeleccionada != null) {
        viewModel.onVolver()
    }

    if (partidaSeleccionada == null) {
        PantallaPrincipal(
            partidas = uiState.partidas,
            onPartidaClick = { partida -> viewModel.onPartidaClick(partida) },
            onAnadirClick = { viewModel.onAnadirClick() }
        )
    } else {
        PantallaDetalle(
            partida = partidaSeleccionada,
            onVolver = { viewModel.onVolver() },
            onEditar = { viewModel.onEditarClick(partidaSeleccionada) },
            onBorrar = { viewModel.onBorrarClick(partidaSeleccionada) }
        )
    }

    uiState.partidaABorrar?.let { partida ->
        ConfirmarBorradoDialog(
            partida = partida,
            onCancelar = { viewModel.onCancelarBorrado() },
            onConfirmar = { viewModel.onConfirmarBorrado() }
        )
    }

    if (uiState.mostrarDialogoAlta) {
        PartidaDialog(
            onCancelar = { viewModel.onCerrarDialogoAlta() },
            onGuardar = { juego, fecha, modo, resultado, marcador, duracion, comentario ->
                viewModel.onGuardarNueva(juego, fecha, modo, resultado, marcador, duracion, comentario)
            }
        )
    }

    uiState.partidaAEditar?.let { partida ->
        PartidaDialog(
            partidaAEditar = partida,
            onCancelar = { viewModel.onCerrarDialogoEdicion() },
            onGuardar = { juego, fecha, modo, resultado, marcador, duracion, comentario ->
                viewModel.onGuardarEdicion(partida.id, juego, fecha, modo, resultado, marcador, duracion, comentario)
            }
        )
    }
}