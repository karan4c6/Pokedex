package com.karansyd4.pokedex.ui.compose.library

import com.karansyd4.pokedex.data.model.PokemonCardData


fun getPreviewPokemonCardData() = listOf(
    PokemonCardData(
        imageUrl = "https://raw.githubusercontent.com/karan4c6/APIs/main/pokedex/images/001.jpg",
        pokedexNumber = "001",
        name = "Bulbasaur"
    ),
    PokemonCardData(
        imageUrl = "https://raw.githubusercontent.com/karan4c6/APIs/main/pokedex/images/002.jpg",
        pokedexNumber = "002",
        name = "Ivysaur"
    ),
    PokemonCardData(
        imageUrl = "https://raw.githubusercontent.com/karan4c6/APIs/main/pokedex/images/003.jpg",
        pokedexNumber = "003",
        name = "Venasaur"
    )
)