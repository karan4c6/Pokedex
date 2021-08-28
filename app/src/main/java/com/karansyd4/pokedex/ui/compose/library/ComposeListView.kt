package com.karansyd4.pokedex.ui.compose.library

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.karansyd4.pokedex.data.model.PokemonCardData
import com.karansyd4.pokedex.theme.PokedexTheme


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PokedexGridList(pokedexList: List<PokemonCardData>, onClick: () -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        pokedexList.forEach { pokedexItem ->
            item {
                PokedexCardItem(pokedexItem, onClick)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PokedexCardItem(data: PokemonCardData, onClick: () -> Unit) {

    Card(modifier = Modifier.padding(8.dp)) {
        Surface(color = MaterialTheme.colors.surface, onClick = onClick) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Image(
                        painter = rememberImagePainter(data.imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = data.pokedexNumber,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = data.name, style = MaterialTheme.typography.h5, color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

//////////////////////////////////////////////////////////////////////////
//                                                                      //
//                                                                      //
//                                                                      //
// Preview Methods Below                                                //
//                                                                      //
//                                                                      //
//                                                                      //
//////////////////////////////////////////////////////////////////////////

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLightPokedexGridList() {
    PokedexTheme(darkTheme = false) {
        PokedexGridList(pokedexList = getPreviewPokemonCardData(), {})
    }
}


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDarkPokedexGridList() {
    PokedexTheme(darkTheme = true) {
        PokedexGridList(pokedexList = getPreviewPokemonCardData(), {})
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewLightPokedexCardItem() {
    PokedexTheme(darkTheme = false) {
        PokedexCardItem(
            data = PokemonCardData(
                imageUrl = "https://raw.githubusercontent.com/karan4c6/APIs/main/pokedex/images/001.jpg",
                pokedexNumber = "001",
                name = "Bulbasaur"
            )
        ) {}
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewDarkPokedexCardItem() {
    PokedexTheme(darkTheme = true) {
        PokedexCardItem(
            data = PokemonCardData(
                imageUrl = "https://raw.githubusercontent.com/karan4c6/APIs/main/pokedex/images/001.jpg",
                pokedexNumber = "001",
                name = "Bulbasaur"
            )
        ) {}
    }
}
