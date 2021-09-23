package com.karansyd4.pokedex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonCardData(val imageUrl: String, val pokedexNumber: String, val name: String)
