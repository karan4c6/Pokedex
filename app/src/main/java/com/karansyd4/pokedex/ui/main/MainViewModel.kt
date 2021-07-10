package com.karansyd4.pokedex.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.repository.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel_Kar"
    }

    private val _pokedexData: MutableLiveData<Result<List<Pokedex>>> = MutableLiveData()
    val pokedexData: LiveData<Result<List<Pokedex>>>
        get() = _pokedexData

    fun loadPokedexEntryForNumber(number: Int) {
        viewModelScope.launch {
            Log.d(TAG, "pokedex Size : ${pokedexRepository.getPokedexSize()}")
            pokedexRepository.getPokedexDbData(number).let { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.collectLatest {
                            Log.d(TAG, "Pokedex[${it?.number}] ${it?.id} : ${it?.name} : ${it?.imageUrl}")
                        }
                    }
                    is Result.DatabaseError -> {
                        Log.e(TAG, "loadPokedexEntryForNumber: ${result.message}")
                    }
                    else -> {
                        Log.e(TAG, "loadPokedexEntryForNumber: Error")
                    }
                }
            }
        }
    }

    fun loadData(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.GetPokedexEvents -> {
                    pokedexRepository.getPokedex()
                        .onEach { dataState ->
                            _pokedexData.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    // No action
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetPokedexEvents : MainStateEvent()
    object None : MainStateEvent()
}