package com.example.mymatch

data class Partida(
    val id: String = java.util.UUID.randomUUID().toString(),
    val juego: String,
    val fecha: String,
    val modo: String,
    val resultado: String,
    val marcador: String,
    val duracion: String,
    val comentario: String
)

fun portadaDe(juego: String): Int {
    return when (juego.trim().lowercase()) {
        "rocket league" -> R.drawable.rocket_league
        "fortnite" -> R.drawable.fortnite
        "valorant" -> R.drawable.valorant
        "gta v" -> R.drawable.gta_v
        "minecraft" -> R.drawable.minecraft
        "ea sports fc 26", "fc 26" -> R.drawable.fc_26
        else -> R.drawable.ic_launcher_foreground
    }
}