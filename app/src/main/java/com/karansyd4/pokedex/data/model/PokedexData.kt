package com.karansyd4.pokedex.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexData(

    @SerialName("data")
    val data: Data,

    @SerialName("status")
    val status: Int
)

@Serializable
data class Data(
    val pokedex: List<Pokedex>
)

@Serializable
data class Pokedex(
    val bestMoveset: BestMoveset,
    val buddy: Buddy,
    val evolution: Evolution,
    val fight: Fight,
    val imageUrl: String,
    val name: String,
    val number: Int,
    val stats: Stats,
    val type: List<String>
)

@Serializable
data class Stats(
    val cp: Int,
    val hp: Int
)

@Serializable
data class Evolution(
    val candy: Int? = null,
    val evolveToName: String? = null,
    val evolveToNumber: Int? = null,
    val mega: Boolean,
    val megaCP: Int? = null,
    val megaEnergy: String? = null
)

@Serializable
data class BestMoveset(
    val charge: String,
    val fast: String,
    val special: String? = null
)

@Serializable
data class Buddy(
    val candyKm: Int
)

@Serializable
data class Fight(
    val weakToType: List<String>
)