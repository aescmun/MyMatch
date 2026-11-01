package com.example.mymatch

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Juego(
    val id: Int,
    val nombre: String,
    val desarrolladora: String,
    val genero: String,
    val plataformas: String,
    val anio: Int,
    val modos: String,
    val portada: Int,
    val descripcion: String
)

val juegosEjemplo = listOf(
    Juego(
        id = 1,
        nombre = "Rocket League",
        desarrolladora = "Psyonix",
        genero = "Deportes",
        plataformas = "PC, PlayStation, Xbox, Switch",
        anio = 2015,
        modos = "1v1, 2v2, 3v3",
        portada = R.drawable.rocket_league,
        descripcion = "Fútbol con coches propulsados por turbo. Cada equipo intenta meter un balón gigante en la portería rival."
    ),
    Juego(
        id = 2,
        nombre = "Fortnite",
        desarrolladora = "Epic Games",
        genero = "Battle royale",
        plataformas = "PC, PlayStation, Xbox, Switch, móvil",
        anio = 2017,
        modos = "Solo, dúos, tríos, escuadrones",
        portada = R.drawable.fortnite,
        descripcion = "Cien jugadores caen en una isla y luchan hasta que solo queda uno o un equipo."
    ),
    Juego(
        id = 3,
        nombre = "Valorant",
        desarrolladora = "Riot Games",
        genero = "Shooter",
        plataformas = "PC, PlayStation, Xbox",
        anio = 2020,
        modos = "No competitivo, competitivo, Deathmatch",
        portada = R.drawable.valorant,
        descripcion = "Shooter cinco contra cinco en el que un equipo ataca para plantar una bomba y el otro defiende."
    ),
    Juego(
        id = 4,
        nombre = "GTA V",
        desarrolladora = "Rockstar North",
        genero = "Mundo abierto",
        plataformas = "PC, PlayStation, Xbox",
        anio = 2013,
        modos = "Historia, GTA Online",
        portada = R.drawable.gta_v,
        descripcion = "Mundo abierto en la ciudad de Los Santos, con un modo historia y un modo online para jugar con amigos."
    ),
    Juego(
        id = 5,
        nombre = "Minecraft",
        desarrolladora = "Mojang Studios",
        genero = "Construcción",
        plataformas = "PC, PlayStation, Xbox, Switch, móvil",
        anio = 2011,
        modos = "Supervivencia, creativo, multijugador",
        portada = R.drawable.minecraft,
        descripcion = "Un mundo hecho de bloques en el que se puede construir casi cualquier cosa, solo o con amigos."
    ),
    Juego(
        id = 6,
        nombre = "EA Sports FC 26",
        desarrolladora = "EA Vancouver",
        genero = "Fútbol",
        plataformas = "PC, PlayStation, Xbox, Switch",
        anio = 2025,
        modos = "Ultimate Team, Carrera, Clubs",
        portada = R.drawable.fc_26,
        descripcion = "Simulador de fútbol con los equipos y jugadores reales de las principales ligas."
    )
)

@Composable
fun PantallaLista(
    juegos: List<Juego>,
    onJuegoClick: (Juego) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "MyMatch",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(juegos, key = { it.id }) { juego ->
                    JuegoCard(
                        juego = juego,
                        onClick = { onJuegoClick(juego) }
                    )
                }
            }
        }
    }
}

@Composable
fun JuegoCard(
    juego: Juego,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(juego.portada),
            contentDescription = juego.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = juego.nombre,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = juego.genero,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun PantallaDetalle(
    juego: Juego,
    onVolver: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(juego.portada),
                contentDescription = juego.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Text(
                text = juego.nombre,
                style = MaterialTheme.typography.headlineMedium
            )
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Desarrolladora: ${juego.desarrolladora}")
                    Text("Género: ${juego.genero}")
                    Text("Plataformas: ${juego.plataformas}")
                    Text("Año: ${juego.anio}")
                    Text("Modos: ${juego.modos}")
                }
            }
            Text(
                text = "Descripción",
                fontWeight = FontWeight.Bold
            )
            Text(text = juego.descripcion)
            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver")
            }
        }
    }
}
