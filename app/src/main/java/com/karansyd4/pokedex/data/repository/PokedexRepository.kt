package com.karansyd4.pokedex.data.repository

import android.util.Log
import com.karansyd4.pokedex.data.local.PokedexDAO
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.model.Result.Companion.NETWORK_OK
import com.karansyd4.pokedex.data.remote.PokedexService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokedexRepository constructor(
    private val pokedexDAO: PokedexDAO,
    private val pokedexService: PokedexService
) : Repository {

    private val TAG = "PokedexRepository_Kar"

    suspend fun getPokedex(): Flow<Result<List<Pokedex>>> = flow {
        emit(Result.Loading)
        try {
            val pokedexData = pokedexService.getPokedexData()
            Log.d(TAG, "getPokedex: NetworkResponse Code : ${pokedexData.status}")

            if (pokedexData.status == NETWORK_OK) {
                // do db insert operation here
//                Log.d(TAG, "getPokedex: ${pokedexData.data.pokedex[0].name}")
                pokedexData.data.pokedex.forEach {
                    Log.d(TAG, "getPokedex: name: ${it.name}")
                }
            } else {
                // error status
            }

            // fetch data from db
//            val cachedPokedexData = blogDao.get()

            emit(Result.Success(pokedexData.data.pokedex))
        } catch (e: Exception) {
            Log.e(TAG, "getPokedex: error Message: ${e.message}")
            Log.e(TAG, "getPokedex: error: ${e.printStackTrace()}")
            emit(Result.InvalidResponseError(e))
        }
    }
}