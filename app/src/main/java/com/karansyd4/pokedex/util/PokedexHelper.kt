package com.karansyd4.pokedex.util

import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.ui.pokedexlist.PokedexCardVO

object PokedexHelper {

    fun getPokedexCards(data: List<Pokedex>, cardClickListener: (PokedexCardVO) -> Unit) =
        data.map { PokedexCardVO(data = it, onClickListener = cardClickListener) }
}