package com.karansyd4.pokedex.data.model

enum class PokemonElementType {
    NORMAL,
    FIGHTING,
    FLYING,
    POISON,
    GROUND,
    ROCK,
    BUG,
    GHOST,
    STEEL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    PSYCHIC,
    ICE,
    DRAGON,
    DARK,
    FAIRY
}

/**
 * [elementType] is strong against returned element type list
 */
fun strongAgainstType(elementType: String) = when (elementType) {
    "FIGHTING" -> listOf("Normal", "Rock", "Steel", "Ice", "Dark")
    "FLYING" -> listOf("Fighting", "Bug", "Grass")
    "POISON" -> listOf("Grass", "Fairy")
    "GROUND" -> listOf("Poison", "Rock", "Steel", "Fire", "Electric")
    "ROCK" -> listOf("Flying", "Bug", "Fire", "Ice")
    "BUG" -> listOf("Grass", "Psychic", "Dark")
    "GHOST" -> listOf("Ghost", "Psychic")
    "STEEL" -> listOf("Rock", "Ice", "Fairy")
    "FIRE" -> listOf("Bug", "Steel", "Grass", "Ice")
    "WATER" -> listOf("Ground", "Rock", "Fire")
    "GRASS" -> listOf("Ground", "Rock", "Water")
    "ELECTRIC" -> listOf("Flying", "Water")
    "PSYCHIC" -> listOf("Fighting", "Poison")
    "ICE" -> listOf("Flying", "Ground", "Grass", "Dragon")
    "DRAGON" -> listOf("Dragon")
    "DARK" -> listOf("Ghost", "Psychic")
    "FAIRY" -> listOf("Fighting", "Dragon", "Dark")
    else -> listOf("")
}

/**
 * [elementType] is weak against returned element type list
 */
fun weakAgainstType(elementType: PokemonElementType) =
    when (elementType) {
        PokemonElementType.NORMAL -> "Fighting"
        PokemonElementType.FIGHTING -> "Flying, Psychic, Fairy"
        PokemonElementType.FLYING -> "Rock, Electric, Ice"
        PokemonElementType.POISON -> "Ground, Psychic"
        PokemonElementType.GROUND -> "Water, Grass, Ice"
        PokemonElementType.ROCK -> "Fighting, Ground, Steel, Water, Grass"
        PokemonElementType.BUG -> "Flying, Rock, Fire"
        PokemonElementType.GHOST -> "Ghost, Dark"
        PokemonElementType.STEEL -> "Fighting, Ground, Fire"
        PokemonElementType.FIRE -> "Ground, Rock, Water"
        PokemonElementType.WATER -> "Grass, Electric"
        PokemonElementType.GRASS -> "Flying, Poison, Bug, Fire, Ice"
        PokemonElementType.ELECTRIC -> "Ground"
        PokemonElementType.PSYCHIC -> "Bug, Ghost, Dark"
        PokemonElementType.ICE -> "Fighting, Rock, Steel, Fire"
        PokemonElementType.DRAGON -> "Ice, Dragon, Fairy"
        PokemonElementType.DARK -> "Fighting, Bug, Fairy"
        PokemonElementType.FAIRY -> "Poison, Steel"
    }
