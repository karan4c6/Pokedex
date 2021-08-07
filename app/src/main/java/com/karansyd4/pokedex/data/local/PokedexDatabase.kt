package com.karansyd4.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karansyd4.pokedex.domain.Converters

@Database(entities = [PokedexEntity::class], version = 6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokedexDAO(): PokedexDAO

    companion object {
        const val DATABASE_NAME: String = "pokedex_db"
    }
}