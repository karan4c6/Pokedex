package com.karansyd4.pokedex.data.remote

import com.karansyd4.pokedex.data.model.PokedexData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PokedexService {

    @GET("main/pokedex.json")
    suspend fun getPokedexData(): PokedexData
}