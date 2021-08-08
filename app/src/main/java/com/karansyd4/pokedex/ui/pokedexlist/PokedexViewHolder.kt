package com.karansyd4.pokedex.ui.pokedexlist

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karansyd4.pokedex.databinding.PokedexListItemBinding
import com.karansyd4.pokedex.util.Util.getPokemonImageUrl
import com.karansyd4.pokedex.util.clickWithDebounce
import com.karansyd4.pokedex.util.padPokedexNumber

class PokedexViewHolder(private val pokedexItemBinding: PokedexListItemBinding) :
    RecyclerView.ViewHolder(pokedexItemBinding.root) {

    companion object {
        private const val TAG = "PokedexViewHolder_Kar"
    }

    fun bind(pokedexCardVO: PokedexCardVO) = with(pokedexItemBinding) {
        pokedexNumber.text = pokedexCardVO.data.number.padPokedexNumber()
        pokemonName.text = pokedexCardVO.data.name
        pokemonImage.load(getPokemonImageUrl(pokedexCardVO.data.number.padPokedexNumber()))

        pokedexItemBinding.root.clickWithDebounce {
            pokedexCardVO.onClickListener(pokedexCardVO)
        }
    }
}
