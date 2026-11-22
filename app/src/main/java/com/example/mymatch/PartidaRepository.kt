package com.example.mymatch

object PartidaRepository {
    private val partidas = mutableListOf(
        Partida(
            juego = "Rocket League",
            fecha = "28/10/2026",
            modo = "2v2",
            resultado = "Victoria",
            marcador = "3-1",
            duracion = "6",
            comentario = "Partido complicado con mucha emoción en los últimos 2 minutos. Metí un gol de chilena y paré un tiro a puerta vacía."
        ),
        Partida(
            juego = "Fortnite",
            fecha = "27/10/2026",
            modo = "Dúos",
            resultado = "Top 3",
            marcador = "5 eliminaciones",
            duracion = "22",
            comentario = "Llegamos a la última zona con poca vida y nos eliminaron desde lejos."
        ),
        Partida(
            juego = "Valorant",
            fecha = "26/10/2026",
            modo = "Competitivo",
            resultado = "Derrota",
            marcador = "11-13",
            duracion = "45",
            comentario = "Íbamos ganando 10-6 pero nos remontaron en las últimas rondas."
        ),
        Partida(
            juego = "GTA V",
            fecha = "25/10/2026",
            modo = "Golpe Fleeca",
            resultado = "Completado",
            marcador = "",
            duracion = "30",
            comentario = "Golpe completado a la primera sin que nos pillara la policía."
        )
    )

    fun getPartidas(): List<Partida> = partidas.toList()

    fun getPartidaById(id: String): Partida? = partidas.find { it.id == id }

    fun addPartida(partida: Partida) {
        partidas.add(partida)
    }

    fun updatePartida(partida: Partida) {
        val index = partidas.indexOfFirst { it.id == partida.id }
        if (index != -1) {
            partidas[index] = partida
        }
    }

    fun deletePartida(id: String) {
        partidas.removeAll { it.id == id }
    }
}