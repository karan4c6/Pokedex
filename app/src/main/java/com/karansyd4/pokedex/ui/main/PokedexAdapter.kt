package com.karansyd4.pokedex.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.databinding.PokedexListItemBinding

class PokedexAdapter(
    var pokedexItemsList: List<PokedexCardVO>
) : RecyclerView.Adapter<PokedexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val baseBinding = PokedexListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokedexViewHolder(baseBinding)
    }

    override fun getItemCount() = pokedexItemsList.size

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        holder.bind(pokedexItemsList[position])
    }
}

data class PokedexCardVO(
    val data: Pokedex,
    val onClickListener: (PokedexCardVO) -> Unit
)