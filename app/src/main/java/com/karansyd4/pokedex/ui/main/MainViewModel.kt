package com.karansyd4.pokedex.ui.main

import androidx.lifecycle.*
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.repository.PokedexRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _pokedexData: MutableLiveData<Result<List<Pokedex>>> = MutableLiveData()

    val pokedexData: LiveData<Result<List<Pokedex>>>
        get() = _pokedexData

    fun setStateEvent(mainStateEvent: MainStateEvent) {
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