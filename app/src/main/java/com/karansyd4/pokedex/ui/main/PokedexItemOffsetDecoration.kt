package com.karansyd4.pokedex.ui.main

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.karansyd4.pokedex.util.isEven

class PokedexItemOffsetDecoration(private val itemOffset: Int) : RecyclerView.ItemDecoration() {

    companion object {
        private const val TAG = "PokedexItemOffset_Kar"
        const val DEFAULT_ITEM_OFFSET = 0

        /**
         * The constant value for large font scale value.
         */
        const val FONT_SCALE_LARGE = 1.2f

        const val MOBILE_MAX_INITIAL_VISIBLE_ITEMS = 2

        /**
         * No. of columns to display for GridLayout for Tablet device when the font size is default.
         */
        const val TABLET_HELP_GRID_COLUMNS_DEFAULT_FONT = 4

        /**
         * Item position starts from 0
         */
        const val FIRST_ITEM_POSITION = 0
        const val SECOND_ITEM_POSITION = 1
    }

    private var itemCount: Int? = null

    private var leftItemOffset = DEFAULT_ITEM_OFFSET
    private var topItemOffset = DEFAULT_ITEM_OFFSET
    private var rightItemOffset = DEFAULT_ITEM_OFFSET
    private var bottomItemOffset = DEFAULT_ITEM_OFFSET

    constructor(@NonNull context: Context, @DimenRes itemOffsetId: Int, size: Int) : this(
        context.resources.getDimensionPixelSize(itemOffsetId)
    ) {
        itemCount = size
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position: Int = parent.getChildAdapterPosition(view)
        Log.d(TAG, "getItemOffsets: $itemOffset")

        leftItemOffset = 12
        topItemOffset = 12
        rightItemOffset = 12
        bottomItemOffset = 12

        /* ifLet(parent.context, itemCount) { (paramOne, paramTwo) ->
             val context = paramOne as Context
             val itemCount = paramTwo as Int

             val fontScale = context.resources.configuration.fontScale
             if (context.isTablet()) { // Tablet design
                 setTabletItemOffset(fontScale, itemCount, position)
             } else { // Mobile Design
                 setMobileItemOffset(itemCount, fontScale, position)
             }
         }*/
        outRect.set(leftItemOffset, topItemOffset, rightItemOffset, bottomItemOffset)
    }

    private fun setTabletItemOffset(fontScale: Float, itemCount: Int, position: Int) {

        topItemOffset = itemOffset
        rightItemOffset = itemOffset
        if (fontScale > FONT_SCALE_LARGE) {
            if (position.isEven()) {
                leftItemOffset = itemOffset
            }
        } else {
            if (position % TABLET_HELP_GRID_COLUMNS_DEFAULT_FONT == FIRST_ITEM_POSITION) {
                leftItemOffset = itemOffset
            }
        }
    }

    private fun setMobileItemOffset(itemCount: Int, fontScale: Float, position: Int) {
        val positionOfCta = MOBILE_MAX_INITIAL_VISIBLE_ITEMS

        if (fontScale > FONT_SCALE_LARGE) { // Vertical Layout Scenario
            setVerticalLayoutMobileItemOffset(position, positionOfCta, itemCount)
        } else { // Grid Layout Scenario
            setGridLayoutMobileItemOffset(position, itemCount, positionOfCta)
        }
    }

    private fun setVerticalLayoutMobileItemOffset(position: Int, positionOfCta: Int, itemCount: Int) {
        leftItemOffset = DEFAULT_ITEM_OFFSET
        topItemOffset = DEFAULT_ITEM_OFFSET
        rightItemOffset = DEFAULT_ITEM_OFFSET
        bottomItemOffset = itemOffset
    }

    private fun setGridLayoutMobileItemOffset(position: Int, itemCount: Int, positionOfCta: Int) {

    }


}