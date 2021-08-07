package com.karansyd4.pokedex.data.repository

import android.util.Log
import com.karansyd4.pokedex.data.local.PokedexDAO
import com.karansyd4.pokedex.data.local.PokedexEntity
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.model.Result.Companion.NETWORK_ERROR
import com.karansyd4.pokedex.data.model.Result.Companion.NETWORK_OK
import com.karansyd4.pokedex.data.remote.PokedexService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokedexRepository constructor(
    private val pokedexDAO: PokedexDAO,
    private val pokedexService: PokedexService
) : Repository {

    companion object {
        const val TAG = "PokedexRepository_Kar"
    }

    suspend fun getPokedexDbData(number: Int) = flow {
        Log.d(TAG, "getPokedexDbData: $number")
        try {
            Log.d(TAG, "getPokedexDbData: Emit: $number")
            emit(Result.Success(pokedexDAO.getPokedexByNumber(number)))
        } catch (e: Exception) {
            emit(Result.Error("Error fetching Pokedex By Number: $number"))
        }
    }

    suspend fun getPokedex(): Flow<Result<List<Pokedex>>> = flow {
        emit(Result.Loading)
        try {
            // get Data from DB and return, also make api call and then store it in db.
            val pokedexData = pokedexService.getPokedexData()
            Log.d(TAG, "getPokedex: NetworkResponse Code : ${pokedexData.status}")
            when (pokedexData.status) {
                NETWORK_OK -> {
                    saveDataToDb(pokedexData.data.pokedex)
                    emit(Result.Success(pokedexData.data.pokedex))
                }
                NETWORK_ERROR -> emit(Result.NetworkError(getCacheData()))
                else -> emit(Result.Error("Error"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "getPokedex: error Message: ${e.message}")
            emit(Result.Error(e.message ?: "Error: ${e.printStackTrace()}"))
        }
    }

    private suspend fun saveDataToDb(pokedexList: List<Pokedex>) = with(Dispatchers.IO) {
        Log.d(TAG, "saveDataToDb: list size: ${pokedexList.size}")
        pokedexList.map {
            PokedexEntity(
                imageUrl = it.imageUrl,
                name = it.name,
                number = it.number,
                type = it.type,
                evolveCandy = it.evolution.candy,
                evolveToName = it.evolution.evolveToName,
                evolveToNumber = it.evolution.evolveToNumber,
                mega = it.evolution.mega,
                megaEnergy = it.evolution.megaEnergy,
                buddyCandyKm = it.buddy.candyKm,
                fastMove = it.bestMoveset.fast,
                chargedMove = it.bestMoveset.charge,
                specialMove = it.bestMoveset.special,
            )
        }.let {
            Log.d(TAG, "saveDataToDb: entity size: ${it.size}")
            pokedexDAO.insertAllPokedexItem(it)
        }
    }

    suspend fun getPokedexSize() = pokedexDAO.getPokedexSize()

    suspend fun getAllPokedexDbData() = try {
        Result.Success(pokedexDAO.getPokedex())
    } catch (e: Exception) {
        Result.Error("Error fetching all Pokedex Db Data")
    }

    suspend fun getPokedexDetail(number: Int) = pokedexDAO.getPokedexByNumber(number)

    private fun getCacheData() = emptyList<Pokedex>()
}