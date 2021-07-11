package com.karansyd4.pokedex.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karansyd4.pokedex.data.local.PokedexEntity
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.repository.PokedexRepository
import com.karansyd4.pokedex.ui.main.PokedexEvent.GetPokedexEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel_Kar"
    }

    private val _pokedexData: MutableLiveData<Result<List<Pokedex>>> = MutableLiveData()
    val pokedexData: LiveData<Result<List<Pokedex>>>
        get() = _pokedexData

    private val _pokedexDetail: MutableLiveData<Result<PokedexEntity>> = MutableLiveData()
    val pokedexDetail: LiveData<Result<PokedexEntity>>
        get() = _pokedexDetail

    fun loadData(pokedexEvent: PokedexEvent) {
        viewModelScope.launch {
            when (pokedexEvent) {
                is GetPokedexEvent -> {
                    pokedexRepository.getPokedex()
                        .onEach { dataState ->
                            _pokedexData.value = dataState
                        }.launchIn(viewModelScope)
                }
                is PokedexEvent.GetPokedexByNumberEvent -> {
                    pokedexRepository.getPokedexDbData(pokedexEvent.number).onEach {
                        _pokedexDetail.value = it
                    }.launchIn(viewModelScope)
                }
                is PokedexEvent.None -> {
                    // No action
                }
            }
        }
    }
}

sealed class PokedexEvent {
    object GetPokedexEvent : PokedexEvent()
    class GetPokedexByNumberEvent(val number: Int) : PokedexEvent()
    object None : PokedexEvent()
}