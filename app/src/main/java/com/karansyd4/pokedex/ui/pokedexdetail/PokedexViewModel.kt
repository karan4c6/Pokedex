package com.karansyd4.pokedex.ui.pokedexdetail

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karansyd4.pokedex.data.local.PokedexEntity
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.data.repository.PokedexRepository
import com.karansyd4.pokedex.ui.pokedexdetail.PokedexEvent.GetPokedexEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Shared between multiple fragments.
 * - PokedexListFragment and
 * - PokedexDetailFragment
 */
@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    companion object {
        private const val TAG = "MainViewModel_Kar"
    }

    /**
     * Pokedex List Data
     */
    private val _pokedexData: MutableLiveData<Result<List<Pokedex>>> = MutableLiveData()
    val pokedexData: LiveData<Result<List<Pokedex>>>
        get() = _pokedexData

    /**
     * Pokemon Details
     */
    private val _pokedexDetail: MutableLiveData<Result<PokedexEntity>> = MutableLiveData()
    val pokedexDetail: LiveData<Result<PokedexEntity>>
        get() = _pokedexDetail

    fun loadData(pokedexEvent: PokedexEvent) {
        viewModelScope.launch {
            when (pokedexEvent) {
                is GetPokedexEvent -> {
                    pokedexRepository.getPokedex().collectLatest { dataState ->
                        _pokedexData.value = dataState
                    }
                }
                is PokedexEvent.GetPokedexByNumberEvent -> {
                    pokedexRepository.getPokedexDbData(pokedexEvent.number).collectLatest {
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
                            _pokedexDetail.value = it
                    }
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