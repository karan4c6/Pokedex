package com.karansyd4.pokedex.util

// https://stackoverflow.com/questions/35513636/multiple-variable-let-in-kotlin
inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit): Unit? {
    return if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    } else null
}

fun Int.isEven() = this % 2 == 0