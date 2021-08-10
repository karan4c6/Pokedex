package com.karansyd4.pokedex.ui.pokedexdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karansyd4.pokedex.databinding.ItemElementTypeBinding

class ElementTypeGridAdapter(
    var weakToTypeList: List<String>
) : RecyclerView.Adapter<ElementTypeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementTypeViewHolder {
        val baseBinding = ItemElementTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementTypeViewHolder(baseBinding)
    }

    override fun getItemCount() = weakToTypeList.size

    override fun onBindViewHolder(holder: ElementTypeViewHolder, position: Int) {
        holder.bind(weakToTypeList[position])
    }
}