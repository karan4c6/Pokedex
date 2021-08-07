package com.karansyd4.pokedex.util

import javax.inject.Singleton

@Singleton
object Util {

    const val API_BASE_URL = "https://raw.githubusercontent.com/karan4c6/APIs/"

    const val ZERO = 0

    fun getPokemonImageUrl(pokedexNumber: String) =
        "https://raw.githubusercontent.com/karan4c6/APIs/main/pokedex/images/$pokedexNumber.jpg"
}