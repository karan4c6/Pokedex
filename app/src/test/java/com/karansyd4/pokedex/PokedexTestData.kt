package com.karansyd4.pokedex

import com.google.gson.Gson
import com.karansyd4.pokedex.data.model.Data
import com.karansyd4.pokedex.data.model.Pokedex
import java.io.FileReader
import java.io.IOException

object PokedexTestData {

    fun getPokedexData(): Data {
        val response = PokedexTestData::class.java.classLoader?.getResource("data/pokedex.json")
        val gson = Gson()
        var data = Data(emptyList())
        try {
            FileReader(response!!.path).use { reader ->
                // Convert JSON File to Java Object
                data = gson.fromJson(reader, Data::class.java)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList<Pokedex>()
        }
        return data
    }
}