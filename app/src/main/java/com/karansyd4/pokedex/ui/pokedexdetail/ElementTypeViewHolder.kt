package com.karansyd4.pokedex.ui.pokedexdetail

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.karansyd4.pokedex.databinding.ItemElementTypeBinding
import com.karansyd4.pokedex.util.Util

class ElementTypeViewHolder(private val elementTypeBinding: ItemElementTypeBinding) :
    RecyclerView.ViewHolder(elementTypeBinding.root) {

    companion object {
        private const val TAG = "PokedexViewHolder_Kar"
    }

    fun bind(weakToType: String) = with(elementTypeBinding) {

        elementTypeBinding.ivElementIcon.load(Util.getElementImageFromElementType(weakToType.lowercase()))
        elementTypeBinding.txtElementType.text = weakToType

        // A11y
        elementTypeParentLayout.contentDescription = weakToType
    }
}
