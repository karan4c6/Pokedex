package com.karansyd4.pokedex.data.repository

import android.util.Log
import com.karansyd4.pokedex.data.NetworkUtil.RESULT_OK
import com.karansyd4.pokedex.data.local.PokedexDAO
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.remote.PokedexService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val pokedexDAO: PokedexDAO,
    private val pokedexService: PokedexService
) : Repository {

    private val TAG = "PokedexRepository"

    suspend fun getPokedex(): Flow<Result<List<Pokedex>>> = flow {
        emit(Result.Loading)
        delay(1000)
        try {
            val pokedexData = pokedexService.getPokedexData()
            Log.d(TAG, "getBlog: ${pokedexData.status}")

            if (pokedexData.status == RESULT_OK) {
                // do db insert operation here
                Log.d(TAG, "getBlog: ${pokedexData.data.pokedex[0].name}")
            } else {
                // error status
            }

            // fetch data from db
//            val cachedPokedexData = blogDao.get()

            emit(Result.Success(pokedexData.data as List<Pokedex>))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}