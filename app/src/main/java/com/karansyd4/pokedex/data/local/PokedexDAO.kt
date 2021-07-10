package com.karansyd4.pokedex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDAO {

    /**
     * Get all items
     * @return List of Pokedex
     */
    @Suppress("unused")
    @Transaction
    @Query("SELECT * FROM pokedex_table ORDER BY id")
    fun getPokedex(): Flow<List<PokedexEntity>>

    /**
     * Get Pokedex item by Pokedex number
     * @return Pokedex
     */
    @Suppress("unused")
    @Transaction
    @Query("SELECT * FROM pokedex_table WHERE id =:number")
    suspend fun getPokedexByNumber(number: Int): PokedexEntity

    /**
     *Get all pokedex item
     * @return List of PokedexEntity
     */
    @Suppress("unused")
    @Transaction
    @Query("SELECT Count(*) FROM pokedex_table")
    suspend fun getPokedexSize(): Int

    /**
     * Insert all Pokedex
     * @param pokedexItemList inserted into pokedex database
     */
    @Suppress("unused")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokedexItem(pokedexItemList: List<PokedexEntity>)

    /**
     * Delete all Pokedex data
     */
    @Suppress("unused")
    @Query("DELETE FROM pokedex_table")
    suspend fun deleteAllPokedexData()
}
