package com.karansyd4.pokedex.util

import javax.inject.Singleton

@Singleton
object Util {

    const val API_BASE_URL = "https://raw.githubusercontent.com/karan4c6/APIs/"

    const val ZERO = 0

    fun getPokemonImageUrl(pokedexNumber: String) =
        "${API_BASE_URL}main/pokedex/images/$pokedexNumber.jpg"

    fun getElementImageFromElementType(elementType: String) =
        "${API_BASE_URL}main/pokedex/images/element/${elementType.lowercase()}.png"

}