package com.karansyd4.pokedex.ui.compose.library

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PokedexHeadingText(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun PreviewPokedexHeadingText() {
    PokedexHeadingText("Bulbasaur")
}