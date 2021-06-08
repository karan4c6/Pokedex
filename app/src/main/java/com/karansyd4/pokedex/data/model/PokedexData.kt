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
    val type: Type
)

@Serializable
data class Stats(
    val cp: Int,
    val hp: Int
)

@Serializable
data class Type(
    val type1: String,
    val type2: String
)

@Serializable
data class WeakType(
    val type1: String,
    val type2: String
)

@Serializable
data class Evolution(
    val candy: Int,
    val evolveToName: String,
    val evolveToNumber: Int,
    val mega: Boolean
)

@Serializable
data class BestMoveset(
    val charge: String,
    val fast: String,
    val special: String
)

@Serializable
data class Buddy(
    val candyKm: Int
)

@Serializable
data class EffectiveType(
    val type1: String,
    val type2: String,
    val type3: String
)

@Serializable
data class Fight(
    val effectiveType: EffectiveType,
    val weakType: WeakType
)