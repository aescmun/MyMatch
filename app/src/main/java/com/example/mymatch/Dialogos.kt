package com.example.mymatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmarBorradoDialog(
    partida: Partida,
    onCancelar: () -> Unit,
    onConfirmar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Eliminar partida") },
        text = { Text("¿Seguro que quieres eliminar la partida de ${partida.juego} del ${partida.fecha}?") },
        confirmButton = {
            TextButton(
                onClick = onConfirmar,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        isError = error != null,
        supportingText = if (error != null) {
            { Text(error) }
        } else {
            null
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PartidaDialog(
    partidaAEditar: Partida? = null,
    onCancelar: () -> Unit,
    onGuardar: (
        juego: String,
        fecha: String,
        modo: String,
        resultado: String,
        marcador: String,
        duracion: String,
        comentario: String
    ) -> Unit
) {
    var juego by remember { mutableStateOf(partidaAEditar?.juego ?: "") }
    var fecha by remember { mutableStateOf(partidaAEditar?.fecha ?: "") }
    var modo by remember { mutableStateOf(partidaAEditar?.modo ?: "") }
    var resultado by remember { mutableStateOf(partidaAEditar?.resultado ?: "") }
    var marcador by remember { mutableStateOf(partidaAEditar?.marcador ?: "") }
    var duracion by remember { mutableStateOf(partidaAEditar?.duracion ?: "") }
    var comentario by remember { mutableStateOf(partidaAEditar?.comentario ?: "") }

    var showErrors by remember { mutableStateOf(false) }

    val juegoError = if (juego.isBlank()) "El juego es obligatorio" else null
    val fechaError = when {
        fecha.isBlank() -> "La fecha es obligatoria"
        fecha.length != 10 || fecha[2] != '/' || fecha[5] != '/' -> "Escribe la fecha como dd/mm/aaaa"
        else -> null
    }
    val modoError = if (modo.isBlank()) "El modo es obligatorio" else null
    val resultadoError = if (resultado.isBlank()) "El resultado es obligatorio" else null
    val duracionError = if (duracion.isNotBlank() && duracion.toIntOrNull() == null) {
        "La duración tiene que ser un número de minutos"
    } else {
        null
    }

    val isFormValid = juegoError == null && fechaError == null &&
            modoError == null && resultadoError == null && duracionError == null

    AlertDialog(
        onDismissRequest = onCancelar,
        title = {
            Text(if (partidaAEditar == null) "Nueva partida" else "Editar partida")
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormField(
                    value = juego,
                    onValueChange = { juego = it },
                    label = "Juego *",
                    error = if (showErrors) juegoError else null
                )
                FormField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = "Fecha *",
                    error = if (showErrors) fechaError else null
                )
                FormField(
                    value = modo,
                    onValueChange = { modo = it },
                    label = "Modo *",
                    error = if (showErrors) modoError else null
                )
                FormField(
                    value = resultado,
                    onValueChange = { resultado = it },
                    label = "Resultado *",
                    error = if (showErrors) resultadoError else null
                )
                FormField(
                    value = marcador,
                    onValueChange = { marcador = it },
                    label = "Marcador"
                )
                FormField(
                    value = duracion,
                    onValueChange = { duracion = it },
                    label = "Duración (min)",
                    error = if (showErrors) duracionError else null
                )
                FormField(
                    value = comentario,
                    onValueChange = { comentario = it },
                    label = "Comentario"
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (isFormValid) {
                        onGuardar(
                            juego.trim(),
                            fecha.trim(),
                            modo.trim(),
                            resultado.trim(),
                            marcador.trim(),
                            duracion.trim(),
                            comentario.trim()
                        )
                    } else {
                        showErrors = true
                    }
                }
            ) {
                Text(if (partidaAEditar == null) "Guardar" else "Actualizar")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}