package com.karansyd4.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karansyd4.pokedex.data.model.Pokedex

@Database(entities = [Pokedex::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokedexDAO(): PokedexDAO
}