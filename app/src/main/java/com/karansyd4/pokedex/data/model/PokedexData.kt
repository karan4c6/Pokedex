package com.karansyd4.pokedex.data.model

data class PokedexData(
    val data: Data,
    val status: Int
)

data class Data(
    val pokedex: List<Pokedex>
)

data class EffectiveType(
    val type1: String,
    val type2: String,
    val type3: String
)

data class Fight(
    val effectiveType: EffectiveType,
    val weakType: WeakType
)

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

data class Stats(
    val cp: Int,
    val hp: Int
)

data class Type(
    val type1: String,
    val type2: String
)

data class WeakType(
    val type1: String,
    val type2: String
)

data class Evolution(
    val candy: Int,
    val evolveToName: String,
    val evolveToNumber: Int,
    val mega: Boolean
)

data class BestMoveset(
    val charge: String,
    val fast: String,
    val special: String
)

data class Buddy(
    val candyKm: Int
)