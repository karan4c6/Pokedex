package com.karansyd4.pokedex.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.karansyd4.pokedex.views.databinding.BubbleTextViewBinding

class BubbleTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var bubbleText: String? = null
    private val binding = BubbleTextViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleTextView)
        with(typedArray) {
            try {
                bubbleText = getString(R.styleable.BubbleTextView_bubbleText)
                updateLayout(bubbleText)
            } finally {
                recycle()
            }
        }
    }

    private fun updateLayout(bubbleText: String?) {
        binding.bubbleText.text = bubbleText
    }
}
