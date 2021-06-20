package com.karansyd4.pokedex.ui.main

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karansyd4.pokedex.databinding.PokedexListItemBinding

class PokedexViewHolder(private val pokedexItemBinding: PokedexListItemBinding) :
    RecyclerView.ViewHolder(pokedexItemBinding.root) {

    fun bind(pokedexCardVO: PokedexCardVO) = with(pokedexItemBinding) {
        //pokedexNumber.text = pokedexCardVO.data.number.toString()
        pokemonName.text = pokedexCardVO.data.name
        pokemonImage.load(pokedexCardVO.data.imageUrl)
    }
}
