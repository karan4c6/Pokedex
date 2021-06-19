package com.karansyd4.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokedexEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokedexDAO(): PokedexDAO

    companion object {
        const val DATABASE_NAME: String = "pokedex_db"
    }
}